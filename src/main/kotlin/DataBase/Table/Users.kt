package com.KtorPractica.DataBase.Table

import org.jetbrains.exposed.dao.id.IntIdTable

object Users : IntIdTable("users","user_id") {
    val name = varchar("name", 100)
    val gmail = varchar("gmail", 150).uniqueIndex()
    val age = integer("age")
    val money = decimal("money", 15, 2)
}