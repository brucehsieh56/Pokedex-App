package app.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import app.pokedex.common.data.remote.PokemonService
import app.pokedex.common.utils.ConnectionState
import app.pokedex.common.utils.currentConnectivityState
import app.pokedex.ui.theme.PokedexTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pokemonService by lazy {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(PokemonService.BASE_URL)
                .build()
                .create(PokemonService::class.java)
        }

        setContent {
            PokedexTheme {
                LaunchedEffect(key1 = "Load Pokemon") {
                    when (this@MainActivity.currentConnectivityState) {
                        ConnectionState.Available -> {
                            try {
                                val lists = pokemonService.getPokemonList(3, 0)
                                println(lists)

                                val pokemon = pokemonService.getSinglePokemon("bulbasaur")
                                println(pokemon)
                            } catch (t: Throwable) {
                                t.printStackTrace()
                            }
                        }
                        ConnectionState.Unavailable -> {
                            println("No connection")
                        }
                    }
                }
            }
        }
    }
}