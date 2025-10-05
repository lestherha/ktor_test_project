package com.KtorPractica.dto.user

import kotlinx.serialization.Serializable

@Serializable
class UserUpdate(
    val name : String,
    val email : String,
    val age : Int,
    val money : Double
)