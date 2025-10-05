package com.KtorPractica.repositories

import com.KtorPractica.dto.user.UserRequest
import com.KtorPractica.dto.user.UserResponse
import com.KtorPractica.dto.user.UserUpdate
import com.KtorPractica.domain.Pagination
import com.KtorPractica.dto.user.UserResponseGame

interface UserRepository {
    suspend fun allUser(page:Int, pageSize: Int): Pagination<UserResponse>
    suspend fun userById(id:Int): UserResponse?
    suspend fun userByName(page:Int, pageSize: Int, name:String): Pagination<UserResponse>
    suspend fun addUser(user: UserRequest)
    suspend fun updateUser(id: Int, user: UserUpdate): Int
    suspend fun removeUser(id:Int):Boolean

}