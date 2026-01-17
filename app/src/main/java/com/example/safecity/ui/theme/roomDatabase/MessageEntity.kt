package com.example.safecity.ui.theme.roomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val message: String,
)