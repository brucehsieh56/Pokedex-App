package app.pokedex.pokemonlistscreen.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pokedex.common.data.remote.PokemonService
import app.pokedex.pokemonlistscreen.domain.model.Pokemon
import kotlinx.coroutines.launch

class PokemonListScreenViewModel(
    private val pokemonService: PokemonService
) : ViewModel() {

    var pokemonList = mutableStateOf<List<Pokemon>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            val response = pokemonService.getPokemonList(10, 0)
            pokemonList.value = response.results.map { result ->

                val number = result.url.dropLast(1).takeLastWhile { it.isDigit() }

                Pokemon(
                    name = result.name,
                    imageUrl = "${Pokemon.baseImageUrl}${number}.png",
                    number = number.toInt()
                )
            }
        }
    }
}