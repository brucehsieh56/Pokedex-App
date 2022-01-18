package app.pokedex.pokemonlistscreen.domain.usecases

import app.pokedex.common.data.PokemonRepository
import app.pokedex.common.data.remote.dto.PokemonListDto
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(limit: Int, offset: Int): PokemonListDto {
        return pokemonRepository.getPokemonList(limit, offset)
    }
}