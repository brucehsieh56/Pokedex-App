package app.pokedex.common.data.remote

import app.pokedex.common.data.remote.dto.PokemonDto
import app.pokedex.common.data.remote.dto.PokemonListDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<PokemonListDto>

    @GET("pokemon/{name}")
    fun getSinglePokemon(
        @Path("name") name: String
    ): Call<PokemonDto>

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}