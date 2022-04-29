package com.example.myapplication.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.example.myapplication.roomDatabase.Message
import com.example.myapplication.roomDatabase.MessageDatabase
import com.example.myapplication.roomDatabase.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val allMessages: LiveData<List<Message>>
    private val repository: MessageRepository
    init {
        val dao = MessageDatabase.getDatabase(application).getMessageDao()
        repository = MessageRepository(dao)
        allMessages = repository.allMessages
    }

    fun deleteMessage(message: Message) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(message)
    }

    fun insertMessage(message: Message) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(message)
    }
}