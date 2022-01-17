package app.pokedex.common.domain.model

import androidx.compose.ui.graphics.Color
import app.pokedex.R

/**
 * List of Pokemon types and the corresponding icons and colors.
 * */
enum class PokemonType {
    BUG, DRAGON, ELECTRIC, FAIRY, FIGHTING, FIRE, FLYING, GRASS, GHOST, GROUND, ICE, NORMAL, POISON,
    PSYCHIC, ROCK, STEEL, WATER;

    companion object {
        fun getCorrespondingIcon(type: PokemonType): Int {
            return when (type) {
                BUG -> R.drawable.ic_type_bug
                DRAGON -> R.drawable.ic_type_dragon
                ELECTRIC -> R.drawable.ic_type_electric
                FAIRY -> R.drawable.ic_type_fairy
                FIGHTING -> R.drawable.ic_type_fighting
                FIRE -> R.drawable.ic_type_fire
                FLYING -> R.drawable.ic_type_flying
                GRASS -> R.drawable.ic_type_grass
                GHOST -> R.drawable.ic_type_ghost
                GROUND -> R.drawable.ic_type_ground
                ICE -> R.drawable.ic_type_ice
                NORMAL -> R.drawable.ic_type_normal
                POISON -> R.drawable.ic_type_poison
                PSYCHIC -> R.drawable.ic_type_psychic
                ROCK -> R.drawable.ic_type_rock
                STEEL -> R.drawable.ic_type_steel
                WATER -> R.drawable.ic_type_water
            }
        }

        fun getCorrespondingColor(type: PokemonType): Color {
            return when (type) {
                BUG -> Color(124, 158, 77)
                DRAGON -> Color(207, 109, 88)
                ELECTRIC -> Color(234, 214, 87)
                FAIRY -> Color(244, 188, 230)
                FIGHTING -> Color(199, 109, 54)
                FIRE -> Color(237, 132, 62)
                FLYING -> Color(189, 185, 184)
                GRASS -> Color(165, 203, 98)
                GHOST -> Color(119, 99, 159)
                GROUND -> Color(168, 153, 80)
                ICE -> Color(113, 193, 227)
                NORMAL -> Color(165, 172, 175)
                POISON -> Color(177, 129, 196)
                PSYCHIC -> Color(222, 109, 178)
                ROCK -> Color(160, 140, 56)
                STEEL -> Color(163, 182, 184)
                WATER -> Color(89, 144, 191)
            }

        }
    }
}
