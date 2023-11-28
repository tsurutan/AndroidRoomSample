package com.example.sampleapp2.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EntryDao {
    @Query("SELECT * FROM entities")
    fun getAll(): List<EntryEntity>

    @Insert
    fun insertAll(vararg entities: EntryEntity)
}