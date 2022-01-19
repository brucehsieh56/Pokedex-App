package app.pokedex.common.routing

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.pokedex.common.routing.ScreenRouter.Companion.KEY_POKEMON_NAME
import app.pokedex.common.utils.empty
import app.pokedex.pokemondetailscreen.presentation.PokemonDetailScreen
import app.pokedex.pokemonlistscreen.presentation.PokemonListScreen
import coil.annotation.ExperimentalCoilApi

@ExperimentalCoilApi
@Composable
fun SetUpRouting(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenRouter.PokemonListScreen.route
    ) {
        composable(route = ScreenRouter.PokemonListScreen.route) {
            PokemonListScreen(navController = navHostController)
        }
        composable(route = "${ScreenRouter.PokemonDetailScreen.route}/{$KEY_POKEMON_NAME}") {
            PokemonDetailScreen(
                pokemonName = it.arguments?.getString(KEY_POKEMON_NAME) ?: String.empty()
            )
        }
    }
}