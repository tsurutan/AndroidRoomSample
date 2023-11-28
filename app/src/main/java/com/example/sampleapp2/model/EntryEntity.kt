package com.example.sampleapp2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sampleapp2.api.Entry

@Entity(tableName = "entities")
data class EntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name="description") val description: String,
    @ColumnInfo(name="category") val category: String,
    @ColumnInfo(name="link") val link: String,
) {
    companion object {
        fun fromEntry(entry: Entry): EntryEntity {
            return EntryEntity(
                description = entry.description,
                category = entry.category,
                link = entry.link
            )
        }
    }
    fun toEntry(): Entry {
        return Entry(
            description = this.description,
            category = this.category,
            link = this.link
        )
    }
}