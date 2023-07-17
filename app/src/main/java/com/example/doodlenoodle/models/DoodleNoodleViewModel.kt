package com.example.doodlenoodle.models

import androidx.lifecycle.AndroidViewModel
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.doodlenoodle.data.entities.Board
import com.example.doodlenoodle.data.entities.BoardList
import com.example.doodlenoodle.data.entities.Doodle
import com.example.doodlenoodle.data.entities.User
import com.example.doodlenoodle.data.repositories.NoodleRepository
import kotlinx.coroutines.launch

// This view model gives the components the capability to interact with the database (and vice versa)

class DoodleNoodleViewModel(appObj: Application): AndroidViewModel(appObj) {

    private val noodleRepository: NoodleRepository = NoodleRepository(appObj)

    fun fetchAllUsers(): LiveData<List<User>> {
        return noodleRepository.readAllUser
    }

    fun fetchAllBoards(): LiveData<List<Board>> {
        return noodleRepository.readAllBoard
    }

    fun getBoardByCode(code: String): LiveData<Board> {
        return noodleRepository.getBoardByCode(code)
    }

    fun insertUser(user: User){
        viewModelScope.launch {
            noodleRepository.insertUser(user)
        }
    }

    fun getUser(id: Int){
        viewModelScope.launch {
            noodleRepository.getUserById(id)
        }
    }

    fun deleteUserById(id: Int){
        viewModelScope.launch {
            noodleRepository.deleteUserByid(id)
        }
    }

    fun createBoard(board: Board){
        viewModelScope.launch {
            noodleRepository.createBoard(board)
        }
    }

    fun fetchAllUserBoards(id: Int): LiveData<BoardList> {
        return noodleRepository.fetchAllUserBoards(id)
    }

    fun deleteBoard(id: Int){
        viewModelScope.launch {
            noodleRepository.deleteBoard(id)
        }
    }

    fun insertDoodle(doodle: Doodle){
        viewModelScope.launch {
            noodleRepository.insertDoodle(doodle)
        }
    }

    fun getDoodleById(id: Int){
        return noodleRepository.getDoodleById(id)
    }

    fun deleteDoodleById(id: Int){
        viewModelScope.launch {
            noodleRepository.deleteDoodleById(id)
        }
    }
}