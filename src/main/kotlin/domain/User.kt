package com.KtorPractica.domain

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var name : String,
    var email : String,
    var age : Int,
    var money : Double,
    var gameList: MutableList<Game> = mutableListOf()
)