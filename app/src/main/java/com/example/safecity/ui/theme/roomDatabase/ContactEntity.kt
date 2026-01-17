package com.example.safecity.ui.theme.roomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="EmergencyContacts")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val phoneNumber: String

)
