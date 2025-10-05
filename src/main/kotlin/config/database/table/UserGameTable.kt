package com.KtorPractica.config.database.table

import org.jetbrains.exposed.sql.Table

object UserGameTable : Table("users_games") {
    val user_id = reference("user_id", UserTable.id)
    val game_id = reference("game_id", GameTable.id)

    override val primaryKey = PrimaryKey(user_id, game_id, name = "PK_user_game")
}