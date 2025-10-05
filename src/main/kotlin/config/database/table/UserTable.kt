package com.KtorPractica.config.database.table

import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable : IntIdTable("users","user_id") {
    val name = varchar("name", 100)
    val email = varchar("email", 150).uniqueIndex()
    val age = integer("age")
    val money = decimal("money", 15, 2)
}
