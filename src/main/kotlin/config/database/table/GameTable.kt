package com.KtorPractica.config.database.table

import org.jetbrains.exposed.dao.id.IntIdTable

object GameTable: IntIdTable("games","game_id") {
    val name = varchar("name", 100)
    val typeGame = varchar("type_game", 100)
    val price = decimal("price", 15, 2)
    val description = varchar("description", 5000)
    val available = bool("available").default(true)
}
