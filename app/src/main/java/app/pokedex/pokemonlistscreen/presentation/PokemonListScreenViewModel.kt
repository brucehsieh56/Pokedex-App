package app.pokedex.pokemonlistscreen.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import app.pokedex.common.presentation.BaseViewModel
import app.pokedex.common.utils.Failure
import app.pokedex.pokemonlistscreen.domain.usecases.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListScreenViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : BaseViewModel() {
    val pokemonList = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            PokemonPagingSource(getPokemonListUseCase = getPokemonListUseCase)
        }
    ).flow.cachedIn(viewModelScope)

    fun onFailureHandle(failure: Failure) {
        handleFailure(failure)
    }

    fun retry() {
        resetFailure()
    }
}