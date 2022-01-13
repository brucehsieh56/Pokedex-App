package app.pokedex.pokemonlistscreen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import app.pokedex.pokemonlistscreen.domain.model.Pokemon

@Composable
fun PokemonListScreen(
    viewModel: PokemonListScreenViewModel
) {

    val items = viewModel.pokemonList.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(
            items = items,
            key = { item: Pokemon -> item.name }
        ) { item ->
            item?.let {
                println(item)
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.h3
                )
            }
        }
    }
}