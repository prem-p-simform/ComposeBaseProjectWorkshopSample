package com.simform.navigation

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.navigation.NavController

/**
 * Navigator command which carries [config] lambda to be applied on [NavController] with navigation command.
 *
 * @param config The config to be carried which will apply config on [NavController]
 */
@Stable
@Immutable
class NavigatorCommand(val config: suspend NavController.() -> Unit) {
    private val someValue = System.nanoTime()

    override fun hashCode(): Int = someValue.hashCode()

    override fun equals(other: Any?): Boolean {
        if (other !is NavigatorCommand) return false
        if (other.someValue != this.someValue) return false
        return true
    }
}
