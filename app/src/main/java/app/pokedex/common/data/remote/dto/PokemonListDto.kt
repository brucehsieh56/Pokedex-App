package app.pokedex.common.data.remote.dto

data class PokemonListDto(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<Result>
) {
    companion object {
        val empty = PokemonListDto(
            count = 0,
            next = null,
            previous = null,
            results = emptyList()
        )
    }
}