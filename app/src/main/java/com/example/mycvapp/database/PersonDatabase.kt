package com.example.mycvapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@TypeConverters(ListConverters::class)
@Database(entities = [Person::class], version = 2)
abstract class PersonDatabase: RoomDatabase() {

    abstract fun personDao(): PersonDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // ...
    }
}