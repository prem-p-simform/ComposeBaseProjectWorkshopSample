package com.simform.navigation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.simform.navigation.database.InMemoryAppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Navigator allows you to navigate detached from compose UI
 */
interface Navigator {
    companion object {
        @Volatile
        private var instance: Navigator? = null

        /**
         * Get the instance of [Navigator].
         *
         * @param context The Android [Context]
         *
         * @return The instance of [Navigator]
         */
        fun getInstance(
            context: Context,
        ): Navigator = instance ?: NavigatorImpl(
            defaultDispatcher = Dispatchers.Default,
            mainDispatcher = Dispatchers.Main,
            resultPublisherDao = InMemoryAppDatabase
                .buildDatabase(context)
                .resultDao()
        ).also { instance = it }
    }

    /**
     * Navigates to a specified [destination]
     *
     * @param destination The [Any] where navigate to
     */
    fun navigate(destination: Any)

    /**
     * Navigate up to previous destination
     */
    fun navigateUp()

    /**
     * Navigate up to a specified [destination]
     *
     * @param destination The [Any] where navigate to
     */
    fun navigateUp(destination: Any)

    /**
     * Navigate to specified [destination] and clears backstack
     *
     * @param destination The [Any] where navigate to
     */
    fun navigateAndClearBackStack(destination: Any)

    /**
     * Popup to a specified [destination] with inclusive
     *
     * @param destination The [Any] popup to
     * @param inclusive Whether the given destination should also be popped
     */
    fun popUpTo(destination: Any, inclusive: Boolean)

    /**
     * Navigate up with specified [key] and [result]
     *
     * @param key The key to identify [result]
     * @param result The result of type [String]
     */
    fun navigateUp(key: String, result: String)

    /**
     * Navigate up to [destination] by carrying a [result] with [key]
     *
     * @param destination The [Any] to navigate up to
     * @param key The key to identify [result]
     * @param result The result of type [String]
     */
    fun navigateUp(destination: Any, key: String, result: String)

    /**
     * Navigate up with a result to send result back to the specified [destination]
     * or if not specified then it'll send result back to previous destination.
     *
     * @param key Unique key to identify
     * @param result The result of type [String]
     * @param destination Optional destination to specify where to send result back
     */
    fun navigateUpWithResult(key: String, result: String, destination: Any? = null)

    /**
     * Navigate up and navigate to a specified [destination]
     *
     * @param destination The destination to navigate to
     */
    fun navigateUpAndNavigateTo(destination: Any)

    /**
     * Observes result for specified [key]
     *
     * @param key The key to observer result for
     * @param singleShot If the event is one time only
     *
     * @return The [Flow] of nullable [String]
     */
    fun observeResult(key: String, singleShot: Boolean = false): Flow<String?>

    /**
     * Attaches the provided controller with the navigator
     *
     * @param controller The [NavHostController] to attach
     */
    suspend fun attachController(controller: NavHostController)

    /**
     * Disposes the current controller
     */
    fun disposeController()
}

/**
 * Navigate up with typesafe [result]
 *
 * @param key The key to identify [result]
 * @param result The result of specified type [T]
 */
inline fun <reified T> Navigator.navigateUp(
    key: String,
    result: T
) {
    navigateUp(key = key, result = Json.encodeToString(result))
}

/**
 * Navigate up to [destination] by carrying a [result] with [key]
 *
 * @param destination The [Any] to navigate up to
 * @param key The key to identify [result]
 * @param result The result of specified type [T]
 */
inline fun <reified T> Navigator.navigateUp(
    destination: Any,
    key: String,
    result: T
) {
    navigateUp(destination = destination, key = key, result = Json.encodeToString(result))
}

/**
 * Observes result for specified [key]
 *
 * @param key The key to observer result for
 * @param singleShot If the event is one time only
 *
 * @return The [Flow] of nullable specified type [T]
 */
inline fun <reified T> Navigator.observeResult(key: String, singleShot: Boolean = false): Flow<T?> =
    observeResult(key = key, singleShot = singleShot)
        .map {
            it?.let {
                Json.decodeFromString<T>(it)
            }
        }

val resultJobHolder: MutableMap<String, Job> = mutableMapOf()

/**
 * Get result from a destination route
 *
 * @param destinationKey Key of destination route
 * @param viewModel ViewModel of the current screen
 * @param jobId Unique identifier of this job
 * @param onResult Callback to handle result of the destination route
 */
inline fun <reified T> Navigator.getResult(
    destinationKey: String,
    viewModel: ViewModel,
    jobId: String = viewModel::class.qualifiedName + "." + destinationKey,
    crossinline onResult: T?.() -> Unit,
) {
    var job: Job? = resultJobHolder.getOrElse(jobId) { null }
    job?.cancel()
    job = viewModel.viewModelScope.launch {
        observeResult<T>(
            key = destinationKey,
            singleShot = true,
        ).collectLatest { result ->
            if (result != null) {
                onResult(result)
                job?.cancel()
                resultJobHolder.remove(jobId)
            }
        }
    }
    resultJobHolder[jobId] = job
    job.invokeOnCompletion {
        job = null
        resultJobHolder.remove(jobId)
    }
}

/**
 * Navigate up with a result to send result back to the specified [destination]
 * or if not specified then it'll send result back to previous destination.
 *
 * @param key Unique key to identify
 * @param result The result of type [T]
 * @param destination Optional destination to specify where to send result back
 */
inline fun <reified T> Navigator.navigateUpWithResult(key: String, result: T, destination: Any? = null) {
    navigateUpWithResult(key = key, result = Json.encodeToString(result), destination = destination)
}
