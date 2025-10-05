package com.KtorPractica.repositories

import com.KtorPractica.repositories.dao.user.*
import com.KtorPractica.dto.user.UserRequest
import com.KtorPractica.dto.user.UserResponse
import com.KtorPractica.dto.user.UserUpdate
import com.KtorPractica.domain.Pagination
import com.KtorPractica.config.database.table.*
import com.KtorPractica.Utils.PaginationRepository
import com.KtorPractica.dto.game.GameOnlyName
import com.KtorPractica.dto.user.UserResponseGame
import com.KtorPractica.repositories.dao.game.GameDAO
import com.KtorPractica.repositories.dao.userGame.UserGameDAO
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.selectAll

class UserRepositoryImpl: UserRepository {
    override suspend fun allUser(page:Int, pageSize: Int): Pagination<UserResponse> = suspendTransaction {
        val allUsers = UserDAO.all()
        PaginationRepository.paginateEntities(
            page,
            pageSize,
            allUsers,
            {it.ToUserResponse()}
        )
    }

    override suspend fun userById(id: Int): UserResponse? = suspendTransaction {
        UserDAO.findById(id)?.ToUserResponse()
    }

    override suspend fun userByName(page:Int, pageSize: Int, name: String): Pagination<UserResponse> = suspendTransaction {
        val userName = UserDAO.find { UserTable.name.lowerCase() like "%${name.lowercase()}%" }

        PaginationRepository.paginateEntities(
            page,
            pageSize,
            userName,
            {it.ToUserResponse()}
        )
    }

    override suspend fun addUser(user: UserRequest):Unit = suspendTransaction {
        UserDAO.new {
            name = user.name
            email = user.email
            age = user.age
            money = user.money.toBigDecimal()
        }
    }

    override suspend fun updateUser(id: Int, user: UserUpdate): Int = suspendTransaction {
        val userDao = UserDAO.findById(id)?: return@suspendTransaction 1

        val existingUser = UserDAO.find { UserTable.email eq user.email }.firstOrNull()
        if(existingUser != null && existingUser.id.value != id)
            return@suspendTransaction 2

        userDao.name = user.name
        userDao.email = user.email
        userDao.age = user.age
        userDao.money = user.money.toBigDecimal()
        return@suspendTransaction 3
    }

    override suspend fun removeUser(id: Int): Boolean = suspendTransaction {
        val rowsDelete = UserTable.deleteWhere {
            UserTable.id eq id
        }
        rowsDelete ==1
    }


}