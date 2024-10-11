package com.simform.navigation.core

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import timber.log.Timber
import kotlin.reflect.KClass

const val DATA_KEY = "data"

/**
 * The navigation argument of the data
 */
val dataArg = navArgument(name = DATA_KEY) {
    type = NavType.StringType
    nullable = true
    defaultValue = null
}

/**
 * Generates a route from [KClass]
 */
inline val <reified T: Any> KClass<out T>.route: String
    get() = this.java.name
        .lowercase()
        .replace(oldChar = '.', newChar = '/')
        .replace(oldChar = '$', newChar = '/') + "?$DATA_KEY={data}"

/**
 * Generates a route for specified [T]
 */
@get:JvmName("NavRouteGetRoute")
inline val <reified T: Any> T.route: String
    get() = this::class.route

/**
 * Parses data from specified [T] and returns as [String]
 *
 * @return The parsed data as [String]
 */
inline fun <reified T: Any> T.parseData(): String {
    val withData = !isAnObject<T>()
    var route = this::class.route
    if (withData) {
        val encoded = NavigationSerializer.encode(value = this)
        route = route.replaceFirst(oldValue = "{data}", newValue = encoded)
    }
    return route
}

/**
 * Get navigation arguments from [NavBackStackEntry]
 *
 * @return The argument of type [T]
 */
inline fun <reified T> NavBackStackEntry.getNavArgs(): T? {
    val data = arguments?.getString(DATA_KEY)

    return when {
        isAnObject<T>() -> getObjectInstance<T>()

        data == null -> {
            val e =
                IllegalArgumentException("Expecting navigation route data for \"${T::class.route}\" but got null!")
            Timber.tag("SaferNavigationCompose").e("${e.message}")
            null
        }

        else -> NavigationSerializer.decode(data)
    }
}

/**
 * Get navigation arguments from [SavedStateHandle]
 *
 * @return The argument of type [T]
 */
inline fun <reified T> SavedStateHandle.getNavArgs(): T? =
    get<String>(DATA_KEY)?.let { NavigationSerializer.decode(it) }

/**
 * Check if [T] is an object, True if it's
 *
 * @return True if [T] is an object type
 */
inline fun <reified T> isAnObject(): Boolean = T::class.java.declaredFields.any {
    it.type == T::class.java && it.name == "INSTANCE"
}

/**
 * Get the object instance
 *
 * @return The object instance
 */
inline fun <reified T> getObjectInstance(): T =
    T::class.java.getDeclaredField("INSTANCE").get(null) as T
