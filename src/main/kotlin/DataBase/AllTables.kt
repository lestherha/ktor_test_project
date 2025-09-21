package com.KtorPractica.DataBase

import com.KtorPractica.DataBase.Table.*
import org.jetbrains.exposed.sql.Table

object AllTables {
    val tables: List<Table> = listOf(
        Users,
        Games,
        Users_Games
    )
}
