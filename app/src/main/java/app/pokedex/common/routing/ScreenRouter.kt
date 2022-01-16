package app.pokedex.common.routing

/**
 * A seal class includes all the navigation destination.
 * */
sealed class ScreenRouter(val route: String) {
    object PokemonListScreen : ScreenRouter(route = ROUTE_POKEMON_LIST_SCREEN)
    object PokemonDetailScreen : ScreenRouter(route = ROUTE_POKEMON_DETAIL_SCREEN)

    companion object {
        const val ROUTE_POKEMON_LIST_SCREEN = "ROUTE_POKEMON_LIST_SCREEN"
        const val ROUTE_POKEMON_DETAIL_SCREEN = "ROUTE_POKEMON_DETAIL_SCREEN"
        const val KEY_POKEMON_NAME = "pokemonName"
    }
}

