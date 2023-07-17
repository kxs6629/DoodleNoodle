package com.example.doodlenoodle.data.converter

import androidx.room.TypeConverter
import com.example.doodlenoodle.data.entities.BoardList
import com.example.doodlenoodle.data.entities.DoodleList
import com.example.doodlenoodle.data.entities.UserList
import com.google.gson.Gson

class Converters {

    // Converters for Boards
    @TypeConverter
    fun fromBoardListToJSON(boardList: BoardList): String?{
        return Gson().toJson(boardList)
    }

    @TypeConverter
    fun fromJSONToBoardList(json: String?): BoardList?{
        return Gson().fromJson(json,BoardList::class.java)
    }

    // Converters for Doodles
    @TypeConverter
    fun fromDoodleListToJSON(doodleList: DoodleList?): String?{
        return Gson().toJson(doodleList)
    }

    @TypeConverter
    fun fromJSONToDoodleList(json: String?): DoodleList?{
        return Gson().fromJson(json,DoodleList::class.java)
    }

    // Converters for Users
    @TypeConverter
    fun fromUserListToJSON(userList: UserList?): String?{
        return Gson().toJson(userList)
    }

    @TypeConverter
    fun fromJSONToUserList(json: String?): UserList?{
        return Gson().fromJson(json,UserList::class.java)
    }


}