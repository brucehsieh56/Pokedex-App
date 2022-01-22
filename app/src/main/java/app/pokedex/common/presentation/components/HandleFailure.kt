package app.pokedex.common.presentation.components

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import app.pokedex.R
import app.pokedex.common.utils.Failure
import java.net.SocketTimeoutException

/**
 * A Composable to handle failures.
 * */
@Composable
fun HandleFailure(
    failure: Failure,
    scaffoldState: ScaffoldState,
    reload: () -> Unit
) {
    val message = when (failure) {
        Failure.NetworkConnection -> stringResource(R.string.failure_no_network_connection)
        is Failure.ServerError -> {
            val throwable = failure.cause
            val message = if (throwable == null) {
                // Non throwable error
                failure.message
            } else {
                // Throwable error
                when (throwable) {
                    is SocketTimeoutException -> stringResource(R.string.failure_server_connection_timeout)
                    else -> {
                        println(throwable)
                        stringResource(R.string.failure_server_error)
                    }
                }
            }
            message
        }
        else -> throw failure
    }

    // Show error message
    if (message != null) {
        val actionLabel = stringResource(id = R.string.action_refresh)
        LaunchedEffect(key1 = "Launch Snackbar") {
            scaffoldState.snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel,
                duration = SnackbarDuration.Indefinite
            ).also {
                when (it) {
                    SnackbarResult.Dismissed -> Unit
                    SnackbarResult.ActionPerformed -> reload()
                }
            }
        }
    }
}