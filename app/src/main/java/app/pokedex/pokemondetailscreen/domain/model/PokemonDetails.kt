package app.pokedex.pokemondetailscreen.domain.model

import app.pokedex.common.data.remote.dto.PokemonDto
import app.pokedex.common.data.remote.dto.Sprites
import app.pokedex.common.data.remote.dto.Stat
import app.pokedex.common.data.remote.dto.Type

/**
 * Data model holds a Pokemon's info for UI.
 * */
data class PokemonDetails(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val stats: List<Stat>,
    val sprites: Sprites? = null,
    val types: List<Type>
) {
    companion object {
        fun fromDomain(pokemonDto: PokemonDto): PokemonDetails {
            return PokemonDetails(
                id = pokemonDto.id,
                name = pokemonDto.name,
                weight = pokemonDto.weight,
                height = pokemonDto.height,
                stats = pokemonDto.stats,
                sprites = pokemonDto.sprites,
                types = pokemonDto.types
            )
        }
    }
}

