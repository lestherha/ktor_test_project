package com.KtorPractica.dto.user

import kotlinx.serialization.Serializable

@Serializable
class UserResponse(
    val id: Int,
    val name : String,
    val email : String,
    val age : Int,
    val money : Double,
)