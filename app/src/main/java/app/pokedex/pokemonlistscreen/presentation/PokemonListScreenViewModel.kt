package app.pokedex.pokemonlistscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import app.pokedex.common.data.PokemonPagingSource
import app.pokedex.common.data.remote.PokemonService

class PokemonListScreenViewModel(
    private val pokemonService: PokemonService
) : ViewModel() {

    val pokemonList = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            PokemonPagingSource(pokemonService = pokemonService)
        }
    ).flow
}