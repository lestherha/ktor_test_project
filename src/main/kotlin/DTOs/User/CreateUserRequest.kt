package com.KtorPractica.DTOs.User

import kotlinx.serialization.Serializable

@Serializable
class CreateUserRequest(
    var name : String,
    var gmail : String,
    var age : Int,
)