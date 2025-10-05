package com.KtorPractica.config.database

import com.KtorPractica.config.database.table.*
import org.jetbrains.exposed.sql.Table

object AllTables {
    val tables: List<Table> = listOf(
        UserTable,
        GameTable,
        UserGameTable
    )
}
