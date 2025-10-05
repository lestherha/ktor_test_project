package com.KtorPractica.config.database

class DataBaseInitializer(
    private val migrations: List<DataBaseMigration>
) {
    fun initialize() {
        migrations.forEach { it.migrate() }
    }
}
