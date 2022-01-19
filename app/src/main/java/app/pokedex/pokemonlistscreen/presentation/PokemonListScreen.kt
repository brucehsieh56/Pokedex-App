package app.pokedex.pokemonlistscreen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import app.pokedex.common.presentation.CenterCircularProgressIndicator
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
    val items = viewModel.pokemonList.collectAsLazyPagingItems()

    // TODO: 1/18/22 Add Pokemon search functionality
    // TODO: 1/18/22 Handle failure

    with(items.loadState) {
        when {
            this.refresh is LoadState.Loading -> CenterCircularProgressIndicator()
            this.refresh is LoadState.Error || this.append is LoadState.Error -> {
                val e = (this.refresh as LoadState.Error)
                when (val failure = e.error as Failure) {
                    Failure.NetworkConnection -> println("network failure")
                    is Failure.ServerError -> println(failure.message)
                    else -> println("Other failure")
                }
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