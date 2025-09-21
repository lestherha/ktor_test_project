package com.KtorPractica.Model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id : Int,
    var name : String,
    var gmail : String,
    var age : Int,
    var money : Double,
    var gameList: MutableSet<Game> = mutableSetOf()
)
