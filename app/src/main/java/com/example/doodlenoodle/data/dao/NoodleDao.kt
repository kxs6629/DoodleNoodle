package com.example.doodlenoodle.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.doodlenoodle.data.entities.Board
import com.example.doodlenoodle.data.entities.BoardList
import com.example.doodlenoodle.data.entities.Doodle
import com.example.doodlenoodle.data.entities.User

//Dao for interacting with AppDatabase and it's respective entities
@Dao
interface NoodleDao {
    @Query("SELECT * FROM user")
    fun fetchAllUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: Int): LiveData<User>

    @Query("DELETE FROM user WHERE id = :id")
    suspend fun deleteUserById(id: Int)

    @Query("SELECT * FROM board")
    fun fetchAllBoards(): LiveData<List<Board>>

    @Query("SELECT user.board_list FROM user WHERE id = :id")
    fun fetchAllUserBoards(id: Int): LiveData<BoardList>

    @Query("SELECT * from board WHERE code = :code")
    fun getBoardByCode(code: String) : LiveData<Board>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createBoard(board: Board)

    @Query("DELETE FROM board WHERE id = :id")
    suspend fun deleteBoard(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDoodle(doodle: Doodle)

    @Query("SELECT * FROM doodle WHERE id = :id")
    fun getDoodleById(id: Int): LiveData<Doodle>

    @Query("DELETE FROM doodle WHERE id = :id")
    suspend fun deleteDoodleById(id: Int)

}