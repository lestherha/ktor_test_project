package com.KtorPractica.DataBase

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class GenericMigration(
    private val tables: List<Table>
) : DataBaseMigration {
    override fun migrate() {
        if (tables.isEmpty()) return
        transaction {
            SchemaUtils.createMissingTablesAndColumns(
                *tables.toTypedArray())
        }
    }
}
