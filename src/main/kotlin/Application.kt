package com.KtorPractica

import com.KtorPractica.DataBase.AllTables
import com.KtorPractica.DataBase.DataBaseFactory
import com.KtorPractica.DataBase.DataBaseInitializer
import com.KtorPractica.DataBase.GenericMigration
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DataBaseFactory.init()//Inicializar BD

    val initializer = DataBaseInitializer(
        listOf(
            GenericMigration(AllTables.tables)
        )
    )
    initializer.initialize()

    configureSerialization()
    configureRouting()

}

