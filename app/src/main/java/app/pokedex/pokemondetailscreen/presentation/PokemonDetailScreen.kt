package app.pokedex.pokemondetailscreen.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import app.pokedex.common.presentation.CenterCircularProgressIndicator
import app.pokedex.common.utils.Failure
import app.pokedex.pokemondetailscreen.presentation.components.PokemonDetailCard

@Composable
fun PokemonDetailScreen(
    pokemonName: String,
    viewModel: PokemonDetailScreenViewModel = hiltViewModel()
) {
    val failure by viewModel.failure.collectAsState()
    val pokemonDetails by viewModel.pokemonDetails.collectAsState()

    LaunchedEffect(key1 = "Load Pokemon detail") {
        viewModel.getSinglePokemon(pokemonName)
    }

    // TODO: 1/18/22 Handle network failure
    when (failure) {
        Failure.NetworkConnection -> println("network failure")
        is Failure.ServerError -> println((failure as Failure.ServerError).message)
        else -> {
            if (pokemonDetails == null) {
                CenterCircularProgressIndicator()
            } else {
                PokemonDetailCard(pokemonDetails!!)
            }
        }
    }
}