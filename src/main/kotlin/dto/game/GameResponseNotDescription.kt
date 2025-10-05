package com.KtorPractica.dto.game

import kotlinx.serialization.Serializable

@Serializable
class GameResponseNotDescription (
    val id: Int,
    val name: String,
    var typeGame: String,
    var price: Double,
    var available: Boolean = true
)