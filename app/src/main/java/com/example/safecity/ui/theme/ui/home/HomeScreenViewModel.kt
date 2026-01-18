package com.example.safecity.ui.theme.ui.home

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safecity.ui.theme.roomDatabase.ContactEntity
import com.example.safecity.ui.theme.roomDatabase.EmergencyContactDao
import com.example.safecity.ui.theme.roomDatabase.MessageDao
import com.example.safecity.ui.theme.roomDatabase.MessageEntity
import kotlinx.coroutines.launch




class HomeScreenViewModel(private val emergencyContactDao: EmergencyContactDao,private val messageDao: MessageDao): ViewModel() {

    val selectedIdOfMessage = mutableIntStateOf(-1)


         val contacts = mutableStateListOf<ContactEntity>()

        val messages = mutableStateListOf<MessageEntity>()

    var selectedMessageEntity= mutableStateOf<String>( "i am in trouble, i need help")

        var alertMessage =mutableStateOf("help")
       val sentAlert = mutableStateOf<Boolean>(false)

        init {
            getContacts()
            getEmergencyAlertMessage()

        }


    fun deleteContact(id: Int){
        viewModelScope.launch {
            emergencyContactDao.deleteContact(id)
        }
        getContacts()
    }

        fun updateAlertMessage(message: String){
         viewModelScope.launch {
           messageDao.insertAlertMessage(MessageEntity(message = message))
         }
            getEmergencyAlertMessage()
        }

        fun getEmergencyAlertMessage(){
            viewModelScope.launch {
                messages.clear()
                messages.addAll(messageDao.getAllMessages())
            }
        }
        fun deleteAlertMessageById(id: Int) {
            viewModelScope.launch {
                messageDao.deleteMessage(id)
            }
            getEmergencyAlertMessage()
        }

    fun getMessageById(id: Int){
        viewModelScope.launch {
            selectedMessageEntity.value = messageDao.getMessageById(id)
        }
    }
    fun saveCustomMessage(id:Int,isSelected:Boolean){
        viewModelScope.launch {
            messageDao.updateSelectedMessage(id,isSelected)
        }
        for(selectedMessage in messages){
                if(selectedMessage.selectedMessage){
                    selectedMessageEntity.value = selectedMessage.message
                }else{
                    selectedMessageEntity.value = alertMessage.value
                }
            }
        getEmergencyAlertMessage()
    }



     fun saveContacts(name: String, number: String){
        viewModelScope.launch {
            emergencyContactDao.insertContact(ContactEntity(name = name, phoneNumber = number))
        }
         getContacts()
    }

    fun getContacts() {
        viewModelScope.launch {
            contacts.clear()
             contacts.addAll( emergencyContactDao.getAllContacts())
        }

    }
}


