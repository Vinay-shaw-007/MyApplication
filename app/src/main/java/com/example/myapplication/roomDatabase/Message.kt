package com.example.myapplication.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Message_Table")
class Message(@ColumnInfo(name = "Text")val message: String) {
    @PrimaryKey(autoGenerate = true) var id = 0
}