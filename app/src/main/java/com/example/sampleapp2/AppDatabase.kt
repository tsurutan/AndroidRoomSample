package com.example.sampleapp2

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sampleapp2.model.EntryDao
import com.example.sampleapp2.model.EntryEntity

@Database(entities = [EntryEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun entryDao(): EntryDao
}