package com.KtorPractica.DataBase.Table

import org.jetbrains.exposed.sql.Table

object Users_Games : Table("users_games") {
    val user_id = reference("user_id", Users.id)
    val game_id = reference("game_id", Games.id)
    override val primaryKey = PrimaryKey(user_id, game_id, name = "PK_user_game")
}