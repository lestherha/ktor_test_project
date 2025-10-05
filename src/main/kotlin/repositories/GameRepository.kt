package com.KtorPractica.repositories

import com.KtorPractica.dto.game.GameRequest
import com.KtorPractica.dto.game.GameResponse
import com.KtorPractica.dto.game.GameResponseNotDescription
import com.KtorPractica.dto.game.GameUpdate
import com.KtorPractica.domain.Pagination
import com.KtorPractica.repositories.dao.game.GameDAO
import org.jetbrains.exposed.sql.SortOrder

interface GameRepository {
    suspend fun allGame(page:Int, pageSize:Int): Pagination<GameResponse>
    suspend fun allGameNotDescription(page:Int, pageSize:Int): Pagination<GameResponseNotDescription>
    suspend fun gameById(id:Int): GameResponse?
    suspend fun gameByName(page:Int, pageSize:Int, name: String): Pagination<GameResponse>
    suspend fun gameByPrece(page: Int, pageSize: Int, minPrece: Double = 0.0, maxPrece: Double = Double.MAX_VALUE, order: SortOrder = SortOrder.ASC): Pagination<GameResponse>
    suspend fun addGame(game: GameRequest): Unit
    suspend fun updateGame(id: Int, game: GameUpdate): Boolean
    suspend fun removeGame(id:Int): Boolean
}