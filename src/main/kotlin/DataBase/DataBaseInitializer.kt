package com.KtorPractica.DataBase

class DataBaseInitializer(
    private val migrations: List<DataBaseMigration>
) {
    fun initialize() {
        migrations.forEach { it.migrate() }
    }
}
