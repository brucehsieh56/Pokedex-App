package app.pokedex.common.data.remote

import app.pokedex.common.data.remote.dto.PokemonDto
import app.pokedex.common.data.remote.dto.PokemonListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListDto

    @GET("pokemon/{name}")
    suspend fun getSinglePokemon(
        @Path("name") name: String
    ): PokemonDto

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}