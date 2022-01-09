package app.pokedex.common.data.remote.dto

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)