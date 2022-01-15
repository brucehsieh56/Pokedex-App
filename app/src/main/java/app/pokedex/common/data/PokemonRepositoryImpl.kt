package app.pokedex.common.data

import app.pokedex.common.data.remote.PokemonService
import app.pokedex.common.data.remote.dto.PokemonDto
import app.pokedex.common.data.remote.dto.PokemonListDto
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonService
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): PokemonListDto {
        return pokemonService.getPokemonList(limit, offset)
    }

    override suspend fun getSinglePokemon(name: String): PokemonDto {
        return pokemonService.getSinglePokemon(name)
    }
}