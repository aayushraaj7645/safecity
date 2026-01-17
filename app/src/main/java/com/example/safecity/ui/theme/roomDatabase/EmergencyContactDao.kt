package com.example.safecity.ui.theme.roomDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.safecity.ui.theme.ui.Screens

@Dao
interface EmergencyContactDao{

    @Insert
    suspend fun insertContact(contact: ContactEntity)

    @Query("SELECT * FROM EmergencyContacts")
    suspend fun getAllContacts(): List<ContactEntity>

    @Query("DELETE FROM EmergencyContacts WHERE id = :id")
    suspend fun deleteContact(id: Int)
   @Query("SELECT * FROM EmergencyContacts WHERE id = :id")
    suspend fun getContactById(id: Int): ContactEntity?




}