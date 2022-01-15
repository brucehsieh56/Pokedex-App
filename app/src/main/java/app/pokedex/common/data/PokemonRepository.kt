package app.pokedex.common.data

import app.pokedex.common.data.remote.dto.PokemonDto
import app.pokedex.common.data.remote.dto.PokemonListDto

interface PokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int): PokemonListDto

    suspend fun getSinglePokemon(name: String): PokemonDto
}