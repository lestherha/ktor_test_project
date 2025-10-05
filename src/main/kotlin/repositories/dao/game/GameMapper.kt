package com.KtorPractica.repositories.dao.game

import com.KtorPractica.dto.game.GameRequest
import com.KtorPractica.dto.game.GameResponse
import com.KtorPractica.dto.game.GameResponseNotDescription
import com.KtorPractica.dto.game.GameUpdate
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

    suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
        newSuspendedTransaction(Dispatchers.IO, statement = block)

    fun GameDAO.toGameRequest() = GameRequest(
        name = this.name,
        typeGame = this.typeGame,
        price = this.price.toDouble(),
        description = this.description,
        available = this.available
    )

    fun GameDAO.toGameResponse() = GameResponse(
        id = this.id.value,
        name = this.name,
        typeGame = this.typeGame,
        price = this.price.toDouble(),
        description = this.description,
        available = this.available
    )

    fun GameDAO.toGameUpdate() = GameUpdate(
        name = this.name,
        typeGame = this.typeGame,
        price = this.price.toDouble(),
        description = this.description,
        available = this.available
    )

fun GameDAO.toGameResponseNotDescription() = GameResponseNotDescription(
    id = this.id.value,
    name = this.name,
    typeGame = this.typeGame,
    price = this.price.toDouble(),
    available = this.available
)