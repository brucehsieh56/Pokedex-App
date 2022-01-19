package app.pokedex.common.data.remote.dto

import app.pokedex.common.utils.empty

data class PokemonDto(
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<Form>,
    val game_indices: List<GameIndice>,
    val height: Int,
    val held_items: List<HeldItem>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val past_types: List<Any>,
    val species: Species,
    val sprites: Sprites? = null,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
) {
    companion object {
        val empty = PokemonDto(
            emptyList(), 0, emptyList(), emptyList(), 0, emptyList(), -1,
            true, String.empty(), emptyList(), String.empty(), 0, emptyList(), Species.empty,
            null, emptyList(), emptyList(), 0
        )
    }
}