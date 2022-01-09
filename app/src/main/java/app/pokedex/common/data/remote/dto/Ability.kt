package app.pokedex.common.data.remote.dto

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)