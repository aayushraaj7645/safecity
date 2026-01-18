package com.example.safecity.ui.theme.roomDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessageDao {


    @Insert
    suspend fun insertAlertMessage(message: MessageEntity)

    @Query("SELECT * FROM messages")
    suspend fun getAllMessages(): List<MessageEntity>

    @Query("SELECT message FROM messages WHERE id = :id")
    suspend fun getMessageById(id: Int): String

    @Query("DELETE FROM messages WHERE id = :id")
    suspend fun deleteMessage(id: Int)

    @Query("SELECT selectedMessage FROM messages WHERE id = :id")
    suspend fun getSelectedMessageById(id: Int): Boolean


    @Query("""UPDATE messages SET selectedMessage = :isSelected WHERE id = :id """)
    suspend fun updateSelectedMessage(id: Int, isSelected: Boolean)


}