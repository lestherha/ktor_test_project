package com.KtorPractica.Model

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val id:Int,
    val name: String,
    var typeGame: String,
    var price: Double,
    var desciption: String,
    var available: Boolean = true
)