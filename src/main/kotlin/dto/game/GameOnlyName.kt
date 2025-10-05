package com.KtorPractica.dto.game

import kotlinx.serialization.Serializable

@Serializable
class GameOnlyName (
    val id: Int,
    val name: String,
)