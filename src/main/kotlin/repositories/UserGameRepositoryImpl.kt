package com.KtorPractica.repositories

import com.KtorPractica.config.database.table.GameTable
import com.KtorPractica.config.database.table.UserGameTable
import com.KtorPractica.dto.game.GameOnlyName
import com.KtorPractica.repositories.dao.game.GameDAO
import com.KtorPractica.repositories.dao.game.suspendTransaction
import com.KtorPractica.repositories.dao.user.UserDAO
import com.KtorPractica.repositories.dao.userGame.UserGameDAO
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.selectAll

class UserGameRepositoryImpl : UserGameRepository {
    override suspend fun addGameToUser(id_user: Int, id_game: Int): Int = suspendTransaction {
        UserDAO.findById(id_user) ?: return@suspendTransaction 1
        GameDAO.findById(id_game) ?: return@suspendTransaction 2

        val existing = UserGameTable.selectAll().where {
            (UserGameTable.user_id eq id_user) and (UserGameTable.game_id eq id_game)
        }.count() > 0

        if (existing) return@suspendTransaction 3

        UserGameDAO.addGameToUser(id_user, id_game)
        return@suspendTransaction 4
    }


}