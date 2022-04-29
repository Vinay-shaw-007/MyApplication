package com.example.myapplication.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(message: Message)

    @Delete
    fun delete(message: Message)

    @Transaction
    @Query("Select * From Message_Table")
    fun getAllMessages(): LiveData<List<Message>>

//    @Transaction
//    @Query("SELECT * FROM Messa")
//    fun getAllMessages(): LiveData<List<Message>>
}