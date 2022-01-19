package app.pokedex.pokemonlistscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import app.pokedex.pokemonlistscreen.domain.usecases.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListScreenViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {
    val pokemonList = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            PokemonPagingSource(getPokemonListUseCase = getPokemonListUseCase)
        }
    ).flow
}