package com.KtorPractica.repositories.dao.user

import com.KtorPractica.dto.game.GameOnlyName
import com.KtorPractica.dto.user.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

    suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
        newSuspendedTransaction(Dispatchers.IO, statement = block)

    fun UserDAO.ToUserRequest() = UserRequest(
        name = this.name,
        email = this.email,
        age = this.age,
        money = this.money.toDouble()
    )

    fun UserDAO.ToUserResponse() = UserResponse(
        id= this.id.value,
        name = this.name,
        email = this.email,
        age = this.age,
        money = this.money.toDouble()
    )

    fun UserDAO.ToUserUpdate() = UserUpdate(
        name = this.name,
        email = this.email,
        age = this.age,
        money = this.money.toDouble()
    )

    fun UserDAO.ToUserResponseGame(games: List<GameOnlyName>) = UserResponseGame(
        id = this.id.value,
        name = this.name,
        email = this.email,
        age = this.age,
        money = this.money.toDouble(),
        gameList = games
    )