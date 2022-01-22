package app.pokedex.pokemonlistscreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.pokedex.common.routing.ScreenRouter
import app.pokedex.pokemonlistscreen.domain.model.Pokemon
import coil.annotation.ExperimentalCoilApi
import java.util.*

/**
 * A card displaying one pokemon with its name, number and image.
 * */
@ExperimentalCoilApi
@Composable
fun PokemonRowCard(
    item: Pokemon,
    navController: NavController
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {

        val initialColor = MaterialTheme.colors.surface
        var dominantColor by remember {
            mutableStateOf(initialColor)
        }

        Row(
            modifier = Modifier
                .clickable {
                    navController.navigate(
                        route = "${ScreenRouter.PokemonDetailScreen.route}/${item.name}"
                    )
                }
                .background(dominantColor.copy(alpha = 0.25f))
                .fillMaxWidth()
                .defaultMinSize(minHeight = 80.dp)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PokemonPortrait(
                pokemon = item,
                modifier = Modifier
                    .size(76.dp)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                imageScale = 1.4f,
                onMainColorExtracted = {
                    dominantColor = it
                }
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Transparent)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = item.name.replaceFirstChar {
                        it.titlecase(Locale.getDefault())
                    },
                    style = MaterialTheme.typography.h4
                )
                Text(
                    text = item.getFormattedNumberString(),
                    modifier = Modifier.align(End),
                    color = Color.Gray,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.h4
                )
            }
        }
    }
}