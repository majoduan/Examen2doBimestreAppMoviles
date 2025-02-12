package com.example.empresa_empleado.model

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Añadir las nuevas columnas a la tabla existente
        database.execSQL("ALTER TABLE bibliotecas ADD COLUMN latitud REAL NOT NULL DEFAULT 0.0")
        database.execSQL("ALTER TABLE bibliotecas ADD COLUMN longitud REAL NOT NULL DEFAULT 0.0")
    }
}