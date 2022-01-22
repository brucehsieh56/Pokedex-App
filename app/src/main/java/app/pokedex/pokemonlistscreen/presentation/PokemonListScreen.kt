package app.pokedex.pokemonlistscreen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import app.pokedex.common.presentation.components.CenterCircularProgressIndicator
import app.pokedex.common.presentation.components.HandleFailure
import app.pokedex.common.utils.Failure
import app.pokedex.pokemonlistscreen.domain.model.Pokemon
import app.pokedex.pokemonlistscreen.presentation.components.PokemonRowCard
import coil.annotation.ExperimentalCoilApi

@ExperimentalCoilApi
@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    val failure by viewModel.failure.collectAsState()
    val items = viewModel.pokemonList.collectAsLazyPagingItems()

    // TODO: 1/18/22 Add Pokemon search functionality

    Scaffold(scaffoldState = scaffoldState) {
        if (failure != null) {
            HandleFailure(failure!!, scaffoldState) {
                items.retry()
                viewModel.retry()
            }
        } else {
            items.loadState.source.forEach { _, loadState ->
                if (loadState is LoadState.Error) {
                    viewModel.onFailureHandle(loadState.toFailure())
                } else if (loadState is LoadState.Loading) {
                    CenterCircularProgressIndicator(modifier = Modifier.zIndex(zIndex = 1f))
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(
                items = items,
                key = { item: Pokemon -> item.name }
            ) { item ->
                item?.let {
                    PokemonRowCard(item = item, navController = navController)
                }
            }
        }
    }
}

private fun LoadState.Error.toFailure() = error as Failure