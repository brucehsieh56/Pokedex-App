package app.pokedex.pokemonlistscreen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import app.pokedex.pokemonlistscreen.domain.model.Pokemon
import app.pokedex.pokemonlistscreen.presentation.components.PokemonRowCard
import coil.annotation.ExperimentalCoilApi

@ExperimentalCoilApi
@Composable
fun PokemonListScreen(
    viewModel: PokemonListScreenViewModel = hiltViewModel()
) {
    val items = viewModel.pokemonList.collectAsLazyPagingItems()

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
                PokemonRowCard(it)
            }
        }
    }
}