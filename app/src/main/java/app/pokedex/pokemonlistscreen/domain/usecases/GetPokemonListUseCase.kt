package app.pokedex.pokemonlistscreen.domain.usecases

import app.pokedex.common.data.PokemonRepository
import app.pokedex.common.utils.Either
import app.pokedex.common.utils.Failure
import app.pokedex.pokemonlistscreen.domain.model.PokemonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(limit: Int, offset: Int): Either<Failure, PokemonList> {
        return withContext(Dispatchers.IO) {
            repository.getPokemonList(limit, offset)
        }
    }
}