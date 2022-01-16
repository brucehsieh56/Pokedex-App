package app.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import app.pokedex.common.routing.SetUpRouting
import app.pokedex.ui.theme.PokedexTheme
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                val navController = rememberNavController()
                SetUpRouting(navHostController = navController)
            }
        }
    }
}