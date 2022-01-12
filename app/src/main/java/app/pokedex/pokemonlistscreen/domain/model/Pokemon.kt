package app.pokedex.pokemonlistscreen.domain.model

data class Pokemon(
    val name: String,
    val imageUrl: String,
    val number: Int
) {
    fun getFormattedNumber(): String {
        return when {
            number < 10 -> "#00$number"
            number < 100 -> "#0$number"
            else -> "#$number"
        }
    }

    fun getPokemonImage(): String {
        return "$baseImageUrl$number.png"
    }

    companion object {
        const val baseImageUrl =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/"
    }
}

