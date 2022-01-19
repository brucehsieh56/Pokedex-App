package app.pokedex.pokemondetailscreen.presentation

import androidx.lifecycle.viewModelScope
import app.pokedex.common.presentation.BaseViewModel
import app.pokedex.common.utils.Either
import app.pokedex.pokemondetailscreen.domain.model.PokemonDetails
import app.pokedex.pokemondetailscreen.domain.usecases.GetSinglePokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailScreenViewModel @Inject constructor(
    private val getSinglePokemonUseCase: GetSinglePokemonUseCase
) : BaseViewModel() {

    private val _pokemonDetails = MutableStateFlow<PokemonDetails?>(null)
    val pokemonDetails = _pokemonDetails.asStateFlow()

    fun getSinglePokemon(name: String) {
        viewModelScope.launch {
            when (val result = getSinglePokemonUseCase(name)) {
                is Either.Left -> handleFailure(result.a)
                is Either.Right -> _pokemonDetails.value = result.b
            }
        }
    }
}