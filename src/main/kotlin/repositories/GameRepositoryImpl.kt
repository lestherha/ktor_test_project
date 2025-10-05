package com.KtorPractica.repositories

import com.KtorPractica.repositories.dao.game.GameDAO
import com.KtorPractica.repositories.dao.game.suspendTransaction
import com.KtorPractica.repositories.dao.game.toGameResponse
import com.KtorPractica.repositories.dao.game.toGameResponseNotDescription
import com.KtorPractica.dto.game.GameRequest
import com.KtorPractica.dto.game.GameResponse
import com.KtorPractica.dto.game.GameResponseNotDescription
import com.KtorPractica.dto.game.GameUpdate
import com.KtorPractica.domain.Pagination
import com.KtorPractica.config.database.table.GameTable
import com.KtorPractica.Utils.PaginationRepository
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.lowerCase

class GameRepositoryImpl: GameRepository {
    override suspend fun allGame(
        page: Int,
        pageSize: Int
    ): Pagination<GameResponse>  = suspendTransaction{
        val allGame = GameDAO.all()
        PaginationRepository.paginateEntities(
            page,
            pageSize,
            allGame,
            {it.toGameResponse()}
        )
    }

    override suspend fun allGameNotDescription(
        page: Int,
        pageSize: Int
    ): Pagination<GameResponseNotDescription>  = suspendTransaction{
        val allGame = GameDAO.all()
        PaginationRepository.paginateEntities(
            page,
            pageSize,
            allGame,
            {it.toGameResponseNotDescription()}
        )
    }

    override suspend fun gameById(
        id: Int
    ): GameResponse? = suspendTransaction{
        GameDAO.findById(id)?.toGameResponse()
    }

    override suspend fun gameByName(
        page: Int,
        pageSize: Int,
        name: String
    ): Pagination<GameResponse> = suspendTransaction{
        val game = GameDAO.find { GameTable.name.lowerCase() like "%${name.lowercase()}%" }
        PaginationRepository.paginateEntities(
            page,
            pageSize,
            game,
            {it.toGameResponse()}
        )
    }

    override suspend fun gameByPrece(
        page: Int,
        pageSize: Int,
        minPrece: Double,
        maxPrece: Double,
        order: SortOrder
    ): Pagination<GameResponse> = suspendTransaction{
        require(minPrece<=maxPrece) {"El valor 'minPrece' tiene que ser menor o igual a 'maxPrece'"}

        var game = GameDAO.find {
            (GameTable.price greaterEq minPrece.toBigDecimal()) and
                    (GameTable.price lessEq maxPrece.toBigDecimal())
        }.orderBy(GameTable.price to order)

        PaginationRepository.paginateEntities(
            page,
            pageSize,
            game,
            {it.toGameResponse()}
        )
    }

    override suspend fun addGame(
        game: GameRequest
    ): Unit = suspendTransaction{
        GameDAO.new{
            name = game.name
            typeGame = game.typeGame
            price = game.price.toBigDecimal()
            description = game.description
            available = game.available
        }
    }

    override suspend fun updateGame(
        id: Int,
        game: GameUpdate
    ): Boolean = suspendTransaction {
        val gameDOA = GameDAO.findById(id)?: return@suspendTransaction false
        gameDOA.name = game.name
        gameDOA.typeGame = game.typeGame
        gameDOA.price = game.price.toBigDecimal()
        gameDOA.description = game.description
        gameDOA.available = game.available
        return@suspendTransaction true
    }

    override suspend fun removeGame(
        id: Int
    ): Boolean = suspendTransaction {
        val rowsDelete = GameTable.deleteWhere {
            GameTable.id eq id
        }
        rowsDelete == 1
    }

}