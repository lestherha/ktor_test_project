package com.KtorPractica.dto.game

import kotlinx.serialization.Serializable

@Serializable
class GameResponse (
    val id: Int,
    val name: String,
    var typeGame: String,
    var price: Double,
    var description: String,
    var available: Boolean = true
)