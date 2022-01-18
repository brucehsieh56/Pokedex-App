package app.pokedex.pokemondetailscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pokedex.common.data.remote.dto.PokemonDto
import app.pokedex.pokemondetailscreen.domain.usecases.GetSinglePokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailScreenViewModel @Inject constructor(
    private val getSinglePokemonUseCase: GetSinglePokemonUseCase
) : ViewModel() {

    private val _pokemonDetail = MutableStateFlow<PokemonDto?>(null)
    val pokemonDetail = _pokemonDetail.asStateFlow()

    fun getSinglePokemon(name: String) {
        viewModelScope.launch {
            val pokemonDto = getSinglePokemonUseCase(name)
            _pokemonDetail.value = pokemonDto
        }
    }
}