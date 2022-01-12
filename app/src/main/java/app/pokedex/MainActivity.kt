package app.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import app.pokedex.common.data.remote.PokemonService
import app.pokedex.pokemonlistscreen.presentation.PokemonListScreen
import app.pokedex.pokemonlistscreen.presentation.PokemonListScreenViewModel
import app.pokedex.ui.theme.PokedexTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private val pokemonService by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(PokemonService.BASE_URL)
            .build()
            .create(PokemonService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = PokemonListScreenViewModel(pokemonService)

        setContent {
            PokedexTheme {
                PokemonListScreen(viewModel = viewModel)
            }
        }
    }
}