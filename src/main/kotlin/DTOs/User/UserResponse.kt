package com.KtorPractica.DTOs.User

import com.KtorPractica.Model.Game
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    var name : String,
    var gmail : String,
    var age : Int,
    var money : Double,
    var gameList: MutableSet<Game> = mutableSetOf()
)
