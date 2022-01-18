package app.pokedex.common.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
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
        return try {
            val response = getPokemonListUseCase(
                limit = numberOfPokemonRequested,
                offset = currentPage * numberOfPokemonRequested
            )
            val isLastedPage = response.next == null

            val pokemonList = response.results.map { result ->

                val numberString = Pokemon.extractPokemonNumber(result)

                Pokemon(
                    name = result.name,
                    imageUrl = "${Pokemon.baseImageUrl}${numberString}.png",
                    number = numberString.toInt()
                )
            }

            LoadResult.Page(
                data = pokemonList,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (isLastedPage) null else currentPage + 1
            )
        } catch (t: Throwable) {
            LoadResult.Error(t)
        }
    }
}