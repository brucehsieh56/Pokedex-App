package app.pokedex.pokemondetailscreen.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import app.pokedex.common.presentation.components.CenterCircularProgressIndicator
import app.pokedex.common.presentation.components.HandleFailure
import app.pokedex.pokemondetailscreen.presentation.components.PokemonDetailCard

@Composable
fun PokemonDetailScreen(
    pokemonName: String,
    viewModel: PokemonDetailScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    val failure by viewModel.failure.collectAsState()
    val pokemonDetails by viewModel.pokemonDetails.collectAsState()

    LaunchedEffect(key1 = failure) {
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