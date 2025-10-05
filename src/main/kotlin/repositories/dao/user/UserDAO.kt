package com.KtorPractica.repositories.dao.user

import com.KtorPractica.config.database.table.UserTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserDAO>(UserTable)

    var name by UserTable.name
    var email by UserTable.email
    var age by UserTable.age
    var money by UserTable.money

}