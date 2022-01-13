package app.pokedex.pokemonlistscreen.domain.model

import app.pokedex.common.data.remote.dto.Result

data class Pokemon(
    val name: String,
    val imageUrl: String,
    val number: Int
) {
    fun getFormattedNumberString(): String {
        return when {
            number < 10 -> "#00$number"
            number < 100 -> "#0$number"
            else -> "#$number"
        }
    }

    fun getPokemonImageUrl(): String {
        return "$baseImageUrl$number.png"
    }

    companion object {
        const val baseImageUrl =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/"

        /**
         * Extract the pokemon number, as a [String], from the given url.
         * */
        fun extractPokemonNumber(result: Result): String {
            return result.url.dropLast(1).takeLastWhile { it.isDigit() }
        }
    }
}

