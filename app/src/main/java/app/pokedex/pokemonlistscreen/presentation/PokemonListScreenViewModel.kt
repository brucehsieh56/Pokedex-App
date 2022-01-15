package app.pokedex.pokemonlistscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import app.pokedex.common.data.PokemonPagingSource
import app.pokedex.common.data.remote.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListScreenViewModel @Inject constructor(
    private val pokemonService: PokemonService
) : ViewModel() {

    val pokemonList = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            PokemonPagingSource(pokemonService = pokemonService)
        }
    ).flow
}