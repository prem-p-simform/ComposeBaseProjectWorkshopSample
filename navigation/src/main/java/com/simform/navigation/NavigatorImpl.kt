package com.simform.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.simform.navigation.database.dao.ResultPublisherDao
import com.simform.navigation.database.entity.ResultEntity
import com.simform.navigation.core.getBackStackEntry
import com.simform.navigation.core.navigate
import com.simform.navigation.core.popBackStack
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import timber.log.Timber
import java.util.LinkedList
import java.util.Queue
import java.util.concurrent.atomic.AtomicBoolean

/**
 * The concrete implementation of [Navigator]
 *
 * @param defaultDispatcher The Default coroutine dispatcher
 * @param mainDispatcher The Main coroutine dispatcher
 * @param resultPublisherDao The [ResultPublisherDao] instance
 */
internal class NavigatorImpl(
    private val defaultDispatcher: CoroutineDispatcher,
    private val mainDispatcher: CoroutineDispatcher,
    private val resultPublisherDao: ResultPublisherDao,
) : Navigator {

    private val isControllerAttached = AtomicBoolean(false)

    private val _navigationCommands =
        MutableSharedFlow<NavigatorCommand>(extraBufferCapacity = Int.MAX_VALUE)
    private val navigationCommands: SharedFlow<NavigatorCommand> =
        _navigationCommands.asSharedFlow()

    private val pendingCommands: Queue<NavigatorCommand> = LinkedList()
    private var currentControllerJob: Job? = null

    override fun navigate(destination: Any) {
       fireCommand(
            NavigatorCommand {
                navigate(route = destination)
            }
       )
    }

    override fun navigateUp() {
        fireCommand(
            NavigatorCommand { navigateUp() }
        )
    }

    override fun navigateUp(destination: Any) {
        fireCommand(
            NavigatorCommand {
                navigate(destination) {
                    previousBackStackEntry?.destination?.route?.let { previousRoute ->
                        popUpTo(previousRoute) {
                            inclusive = false
                        }
                    }
                }
            }
        )
    }

    override fun navigateAndClearBackStack(destination: Any) {
        fireCommand(
            NavigatorCommand {
                navigate(destination) {
                    popUpTo(0) {
                        inclusive = true
                    }
                }
            }
        )
    }

    override fun popUpTo(destination: Any, inclusive: Boolean) {
        fireCommand(
            NavigatorCommand { popBackStack(destination, inclusive) }
        )
    }

    override fun navigateUp(key: String, result: String) {
        fireCommand(
            NavigatorCommand {
                previousBackStackEntry?.savedStateHandle?.set(key = key, value = result)
                navigateUp()
            }
        )
    }

    override fun navigateUp(destination: Any, key: String, result: String) {
        fireCommand(
            NavigatorCommand {
                getBackStackEntry(destination).savedStateHandle[key] =
                    Json.encodeToString(result)
                popBackStack(destination, false)
            }
        )
    }

    override fun navigateUpWithResult(key: String, result: String, destination: Any?) {
        fireCommand(
            NavigatorCommand {
                resultPublisherDao.insert(result = ResultEntity(key = key, result = result))
                destination?.let {
                    popBackStack(it, false)
                } ?: run {
                    navigateUp()
                }
            }
        )
    }

    override fun navigateUpAndNavigateTo(destination: Any) {
        fireCommand(
            NavigatorCommand {
                navigate(destination) {
                    previousBackStackEntry?.destination?.route?.let { previousRoute ->
                        popUpTo(previousRoute) {
                            inclusive = false
                        }
                    }
                }
            }
        )
    }

    override fun observeResult(key: String, singleShot: Boolean): Flow<String?> {
        val scope = CoroutineScope(defaultDispatcher)
        return resultPublisherDao
            .getResult(key = key)
            .shareIn(
                scope = scope,
                started = SharingStarted.WhileSubscribed(),
                replay = 0
            )
            .map { it?.result }
            .onEach {
                if (it != null && singleShot) {
                    resultPublisherDao.deleteResultByKey(key = key)
                }
            }
    }

    override suspend fun attachController(controller: NavHostController): Unit = coroutineScope {
        currentControllerJob?.cancel()
        Timber.d("Attaching controller with navigator")
        currentControllerJob = launch {
            isControllerAttached.set(false)
            launch {
                executePendingCommands(controller)
            }.invokeOnCompletion {
                Timber.d("Cancelled ---> Pending commands")
            }
            launch(defaultDispatcher) {
                navigationCommands.collect { command ->
                    launch(mainDispatcher) {
                        command.config.invoke(controller)
                    }
                }
            }.invokeOnCompletion {
                Timber.d("Cancelled ---> Commands collector")
            }
            isControllerAttached.set(true)
        }
        Timber.d("Controller has been attached with navigator")
        currentControllerJob?.invokeOnCompletion {
            Timber.d("Controller has been detached with navigator")
            isControllerAttached.set(false)
            currentControllerJob = null
        }
    }
    
    override fun disposeController() {
        currentControllerJob?.cancel()
    }

    private suspend fun executePendingCommands(controller: NavController): Unit = coroutineScope {
        Timber.d("Executing ${pendingCommands.size} pending commands")
        if (isControllerAttached.get() && pendingCommands.isEmpty()) {
            Timber.d("Controller is already attached and no pending commands left to execute")
            return@coroutineScope
        }
        while (pendingCommands.isNotEmpty()) {
            val command = pendingCommands.remove()
            launch(mainDispatcher) {
                command.config.invoke(controller)
            }
        }
        Timber.d("All pending commands has been executed")
    }

    private fun fireCommand(command: NavigatorCommand) {
        if (isControllerAttached.get()) {
            _navigationCommands.tryEmit(command)
        } else {
            pendingCommands.add(command)
        }
    }
}
 