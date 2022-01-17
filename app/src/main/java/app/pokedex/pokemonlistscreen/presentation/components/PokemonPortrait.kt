package app.pokedex.pokemonlistscreen.presentation.components

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette
import app.pokedex.pokemonlistscreen.domain.model.Pokemon
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import coil.size.PixelSize

/**
 * A [Composable] to display the image of a Pokemon.
 * */
@ExperimentalCoilApi
@Composable
fun PokemonPortrait(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
    imageScale: Float = 1f,
    onMainColorExtracted: (Color) -> Unit = {}
) {
    val painter = rememberImagePainter(
        data = pokemon.getPokemonImageUrl(),
        builder = {
//            size(PixelSize(128, 128))
            size(OriginalSize)
            crossfade(true)
        }
    )

    // Handle image
    when (painter.state) {
        ImagePainter.State.Empty -> Box(modifier = modifier) {}
        is ImagePainter.State.Error -> Box(modifier = modifier) {}
        is ImagePainter.State.Loading -> CircularProgressIndicator(modifier = modifier)
        is ImagePainter.State.Success -> {

            val drawable = (painter.state as ImagePainter.State.Success).result.drawable
            val bitmap =
                (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

            Palette.from(bitmap).generate { palette ->
                palette?.dominantSwatch?.rgb?.let {
                    onMainColorExtracted(Color(it))
                }
            }

            Image(
                painter = painter,
                contentDescription = "Pokemon image",
                modifier = modifier.scale(imageScale),
            )
        }
    }
}