package app.pokedex.common.data.remote.dto

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)