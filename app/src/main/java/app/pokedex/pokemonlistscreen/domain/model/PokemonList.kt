package app.pokedex.pokemonlistscreen.domain.model

import app.pokedex.common.data.remote.dto.PokemonListDto

/**
 * Data model holds a list of [Pokemon]s and pagination info for UI.
 * */
data class PokemonList(
    val pokemonList: List<Pokemon>,
    val isLastedPage: Boolean = true
) {
    companion object {
        fun fromDomain(pokemonListDto: PokemonListDto): PokemonList {
            val pokemonList = pokemonListDto.results.map { result ->
                val numberString = Pokemon.extractPokemonNumber(result)
                Pokemon(
                    name = result.name,
                    imageUrl = "${Pokemon.baseImageUrl}${numberString}.png",
                    number = numberString.toInt()
                )
            }
            return PokemonList(
                pokemonList = pokemonList,
                isLastedPage = pokemonListDto.next == null
            )
        }
    }
}
