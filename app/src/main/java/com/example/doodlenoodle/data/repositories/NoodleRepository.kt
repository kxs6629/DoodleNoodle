package com.example.doodlenoodle.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.doodlenoodle.data.AppDatabase
import com.example.doodlenoodle.data.dao.NoodleDao
import com.example.doodlenoodle.data.entities.Board
import com.example.doodlenoodle.data.entities.BoardList
import com.example.doodlenoodle.data.entities.Doodle
import com.example.doodlenoodle.data.entities.User

// This repository is in charge of making calls to the dao to get data as needed back and forth from
// the application to the database
class NoodleRepository(application: Application) {

    private var noodleDao : NoodleDao

    init{
        val database = AppDatabase.getDatabase(application)
        noodleDao = database.noodleDao()
    }

    val readAllUser : LiveData<List<User>> = noodleDao.fetchAllUsers()

    val readAllBoard : LiveData<List<Board>> = noodleDao.fetchAllBoards()


    suspend fun insertUser(user: User){
        noodleDao.insertUser(user)
    }

    suspend fun getUserById(id: Int){
        noodleDao.getUserById(id)
    }

    suspend fun deleteUserByid(id: Int){
        noodleDao.deleteUserById(id)
    }

    suspend fun createBoard(board: Board){
        noodleDao.createBoard(board)
    }

    fun fetchAllUserBoards(id: Int): LiveData<BoardList>{
       return noodleDao.fetchAllUserBoards(id)
    }

    fun getBoardByCode(code: String): LiveData<Board> {
        return noodleDao.getBoardByCode(code)
    }

    suspend fun deleteBoard(id: Int){
        noodleDao.deleteBoard(id)
    }

    suspend fun insertDoodle(doodle: Doodle){
        noodleDao.insertDoodle(doodle)
    }

    fun getDoodleById(id: Int){
        noodleDao.getDoodleById(id)
    }

    suspend fun deleteDoodleById(id: Int){
        noodleDao.deleteDoodleById(id)
    }

}