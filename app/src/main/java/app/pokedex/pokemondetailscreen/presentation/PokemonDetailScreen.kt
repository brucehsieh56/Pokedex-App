package app.pokedex.pokemondetailscreen.presentation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import app.pokedex.R
import app.pokedex.common.presentation.CenterCircularProgressIndicator
import app.pokedex.common.utils.Failure
import app.pokedex.pokemondetailscreen.presentation.components.PokemonDetailCard
import kotlinx.coroutines.delay
import java.net.SocketTimeoutException

@Composable
fun PokemonDetailScreen(
    pokemonName: String,
    viewModel: PokemonDetailScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    val failure by viewModel.failure.collectAsState()
    val pokemonDetails by viewModel.pokemonDetails.collectAsState()

    LaunchedEffect(key1 = failure) {
        delay(300) // For debugging
        failure ?: viewModel.getSinglePokemon(pokemonName)
    }

    Scaffold(scaffoldState = scaffoldState) {
        if (failure == null) {
            if (pokemonDetails == null) {
                CenterCircularProgressIndicator()
            } else {
                PokemonDetailCard(pokemonDetails!!)
            }
        } else {
            HandleFailure(failure!!, scaffoldState, viewModel::reload)
        }
    }
}

/**
 * A Composable to handle failures.
 * */
@Composable
private fun HandleFailure(
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
        else -> null
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