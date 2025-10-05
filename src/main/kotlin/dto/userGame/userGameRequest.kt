package com.KtorPractica.dto.userGame

import kotlinx.serialization.Serializable

@Serializable
class userGameRequest (
    val id_user: Int,
    val id_game: Int
)