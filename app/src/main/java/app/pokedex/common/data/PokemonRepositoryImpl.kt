package app.pokedex.common.data

import app.pokedex.common.data.remote.PokemonService
import app.pokedex.common.data.remote.dto.PokemonDto
import app.pokedex.common.data.remote.dto.PokemonListDto
import app.pokedex.common.utils.Either
import app.pokedex.common.utils.Failure
import app.pokedex.common.utils.empty
import app.pokedex.pokemondetailscreen.domain.model.PokemonDetails
import app.pokedex.pokemonlistscreen.domain.model.PokemonList
import retrofit2.Call
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonService
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Either<Failure, PokemonList> {
        return request(
            pokemonService.getPokemonList(limit, offset),
            { pokemonListDto -> PokemonList.fromDomain(pokemonListDto) },
            PokemonListDto.empty
        )
    }

    override suspend fun getSinglePokemon(name: String): Either<Failure, PokemonDetails> {
        return request(
            pokemonService.getSinglePokemon(name),
            { pokemonDto -> PokemonDetails.fromDomain(pokemonDto) },
            PokemonDto.empty
        )
    }

    /**
     * Execute Http request first and then map the response [T] to [R].
     * */
    private fun <T, R> request(
        call: Call<T>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(
                    Failure.ServerError(
                        message = "${response.code()}\n${response.errorBody()?.string()}"
                    )
                )
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError(message = exception.message ?: String.empty()))
        }
    }
}