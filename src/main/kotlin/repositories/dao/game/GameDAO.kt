package com.KtorPractica.repositories.dao.game

import com.KtorPractica.config.database.table.GameTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class GameDAO(id: EntityID<Int>): IntEntity(id){
    companion object : IntEntityClass<GameDAO>(GameTable)

    var name by GameTable.name
    var typeGame by GameTable.typeGame
    var price by GameTable.price
    var description by GameTable.description
    var available by GameTable.available

}