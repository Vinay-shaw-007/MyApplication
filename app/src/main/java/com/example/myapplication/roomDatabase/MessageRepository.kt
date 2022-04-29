package com.example.myapplication.roomDatabase

import androidx.lifecycle.LiveData

class MessageRepository(private val messageDao: MessageDao) {

    val allMessages: LiveData<List<Message>> = messageDao.getAllMessages()

    fun insert(message: Message){
        messageDao.insert(message)
    }

    fun delete(message: Message){
        messageDao.delete(message)
    }
}