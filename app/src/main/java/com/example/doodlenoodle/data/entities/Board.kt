package com.example.doodlenoodle.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Data class for representing board entity in the database
@Entity(tableName = "board")
data class Board(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "code")
    val code: String?,

    @ColumnInfo(name = "user_list")
    val user_list: UserList?,

    @ColumnInfo(name = "doodle_list")
    val doodle_list: DoodleList?
)