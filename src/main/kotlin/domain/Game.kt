package com.KtorPractica.domain

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    var id:Int,
    var name: String,
    var typeGame: String,
    var price: Double,
    var description: String,
    var available: Boolean = true,
    var userList: MutableList<User> = mutableListOf()
)