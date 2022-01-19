package app.pokedex.common.data.remote.dto

import app.pokedex.common.utils.empty

data class Species(
    val name: String,
    val url: String
) {
    companion object {
        val empty = Species(String.empty(), String.empty())
    }
}