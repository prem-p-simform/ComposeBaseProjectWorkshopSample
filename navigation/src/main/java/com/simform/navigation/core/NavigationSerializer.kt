package com.simform.navigation.core

import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.plus
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import timber.log.Timber
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * Navigation serializer helps with serialization during type safe navigation
 */
@OptIn(ExperimentalEncodingApi::class)
object NavigationSerializer {
    var module: SerializersModule? = null

    val json: Json
        get() = Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            module?.let { serializersModule = it }
        }

    /**
     * Register the route with serialization
     */
    inline fun <reified T : Any> registerRoute() {
        val newModule = SerializersModule {
            polymorphic(Any::class) {
                subclass(T::class)
            }
        }
        val oldModule = module
        module = oldModule?.plus(newModule) ?: newModule
    }

    /**
     * Encode the [value]
     *
     * @param value The value to be encoded
     *
     * @return The encoded string
     */
    inline fun <reified T : Any> encode(value: T): String {
        val data = json.encodeToString(PolymorphicSerializer(Any::class), value)
        return Base64.encode(data.encodeToByteArray())
    }

    /**
     * Decode the value
     *
     * @param value The value to be decoded
     *
     * @return The decoded value as [T]
     */
    inline fun <reified T> decode(value: String): T? = try {
        val decoded = Base64
            .decode(value)
            .toString(charset("UTF-8"))
        json.decodeFromString(decoded)
    } catch (e: IllegalArgumentException) {
        Timber.tag("SaferComposeNavigation").e(e)
        null
    }
}
