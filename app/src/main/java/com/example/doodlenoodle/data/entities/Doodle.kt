package com.example.doodlenoodle.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Data class for representing doodle entity in the database

@Entity(tableName = "doodle")
data class Doodle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "user_id")
    val user_id: Int?,

    @ColumnInfo(name = "image_url")
    val image_url: String?
)