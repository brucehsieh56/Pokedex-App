package app.pokedex.pokemondetailscreen.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun PokemonDetailScreen(pokemonName: String) {
    Text(
        text = "Pokemon Detail Screen: $pokemonName",
        style = MaterialTheme.typography.h3
    )
}