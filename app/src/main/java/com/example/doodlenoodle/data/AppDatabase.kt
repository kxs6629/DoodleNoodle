package com.example.doodlenoodle.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.doodlenoodle.data.converter.Converters
import com.example.doodlenoodle.data.dao.NoodleDao
import com.example.doodlenoodle.data.entities.Board
import com.example.doodlenoodle.data.entities.Doodle
import com.example.doodlenoodle.data.entities.User


// Room database to hold information related to DoodleNoodle
@Database(entities = [User::class, Doodle::class, Board::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun noodleDao(): NoodleDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "noodles" )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}