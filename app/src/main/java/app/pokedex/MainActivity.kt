package app.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import app.pokedex.pokemonlistscreen.presentation.PokemonListScreen
import app.pokedex.ui.theme.PokedexTheme
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                PokemonListScreen()
            }
        }
    }
}