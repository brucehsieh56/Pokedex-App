package app.pokedex.pokemondetailscreen.domain.usecases

import app.pokedex.common.data.PokemonRepository
import app.pokedex.common.data.remote.dto.PokemonDto
import javax.inject.Inject

class GetSinglePokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(name: String): PokemonDto {
        return repository.getSinglePokemon(name)
    }
}