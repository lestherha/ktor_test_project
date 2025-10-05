package com.KtorPractica.dto.user

import com.KtorPractica.dto.game.GameOnlyName
import kotlinx.serialization.Serializable

@Serializable
class UserResponseGame(
    val id: Int,
    val name : String,
    val email : String,
    val age : Int,
    val money : Double,
    val gameList: List<GameOnlyName>
)