package app.pokedex.pokemonlistscreen.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.pokedex.common.utils.Either
import app.pokedex.pokemonlistscreen.domain.model.Pokemon
import app.pokedex.pokemonlistscreen.domain.usecases.GetPokemonListUseCase

class PokemonPagingSource(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : PagingSource<Int, Pokemon>() {

    companion object {
        private const val numberOfPokemonRequested = 30
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val currentPage = params.key ?: 0

        return when (
            val result = getPokemonListUseCase(
                limit = numberOfPokemonRequested,
                offset = currentPage * numberOfPokemonRequested
            )
        ) {
            is Either.Left -> LoadResult.Error(result.a)
            is Either.Right -> {
                val pokemonList = result.b
                LoadResult.Page(
                    data = pokemonList.pokemonList,
                    prevKey = if (currentPage == 0) null else currentPage - 1,
                    nextKey = if (pokemonList.isLastedPage) null else currentPage + 1
                )
            }
        }
    }
}