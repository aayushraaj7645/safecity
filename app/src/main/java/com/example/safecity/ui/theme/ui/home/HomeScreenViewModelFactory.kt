package com.example.safecity.ui.theme.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.safecity.ui.theme.roomDatabase.EmergencyContactDao
import com.example.safecity.ui.theme.roomDatabase.MessageDao

class HomeScreenViewModelFactory (private val emergencyContactDao: EmergencyContactDao,private val messageDao: MessageDao):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if(modelClass.isAssignableFrom(HomeScreenViewModel::class.java)){
           @Suppress("UNCHECKED_CAST")
           return HomeScreenViewModel(emergencyContactDao,messageDao) as T
       }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}