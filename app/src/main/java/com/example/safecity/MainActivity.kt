package com.example.safecity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.safecity.ui.theme.roomDatabase.ContactDatabase
import com.example.safecity.ui.theme.ui.SafeCityApp
import com.example.safecity.ui.theme.ui.home.HomeScreenViewModel
import com.example.safecity.ui.theme.ui.home.HomeScreenViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = ContactDatabase.getInstance(applicationContext)
        val dao = database.emergencyContactDao()
        val messageDao = database.messageDao()
        val factory = HomeScreenViewModelFactory(dao,messageDao)
        setContent {

            val homeScreenViewModel: HomeScreenViewModel = viewModel(factory = factory)

            SafeCityApp(homeScreenViewmodel = homeScreenViewModel)

        }
    }
}



