package com.KtorPractica

import com.KtorPractica.config.database.AllTables
import com.KtorPractica.config.database.DataBaseFactory
import com.KtorPractica.config.database.DataBaseInitializer
import com.KtorPractica.config.database.GenericMigration
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

