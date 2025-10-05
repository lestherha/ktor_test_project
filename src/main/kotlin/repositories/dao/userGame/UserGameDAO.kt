package com.KtorPractica.repositories.dao.userGame

import com.KtorPractica.config.database.table.UserGameTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert

object UserGameDAO {
    fun addGameToUser(userId: Int, gameId: Int) {
        UserGameTable.insert {
            it[UserGameTable.user_id] = userId
            it[UserGameTable.game_id] = gameId
        }
    }

    fun removeGameFromUser(userId: Int, gameId: Int) {
        UserGameTable.deleteWhere {
            (UserGameTable.user_id eq userId) and (UserGameTable.game_id eq gameId)
        }
    }

    fun removeAllGamesFromUser(userId: Int) {
        UserGameTable.deleteWhere { UserGameTable.user_id eq userId }
    }
}