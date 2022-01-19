package app.pokedex.common.data

import app.pokedex.common.utils.Either
import app.pokedex.common.utils.Failure
import app.pokedex.pokemondetailscreen.domain.model.PokemonDetails
import app.pokedex.pokemonlistscreen.domain.model.PokemonList

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): Either<Failure, PokemonList>
    suspend fun getSinglePokemon(name: String): Either<Failure, PokemonDetails>
}