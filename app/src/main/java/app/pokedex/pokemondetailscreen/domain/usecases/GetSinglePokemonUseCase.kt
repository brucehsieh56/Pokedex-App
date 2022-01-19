package app.pokedex.pokemondetailscreen.domain.usecases

import app.pokedex.common.data.PokemonRepository
import app.pokedex.common.utils.Either
import app.pokedex.common.utils.Failure
import app.pokedex.pokemondetailscreen.domain.model.PokemonDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSinglePokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(name: String): Either<Failure, PokemonDetails> {
        return withContext(Dispatchers.IO) {
            repository.getSinglePokemon(name)
        }
    }
}