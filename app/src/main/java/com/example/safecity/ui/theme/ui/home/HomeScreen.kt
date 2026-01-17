package com.example.safecity.ui.theme.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.Face

import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.safecity.ui.theme.ui.BottomNavItem
import com.example.safecity.ui.theme.ui.Screens


@Composable
fun HomeScreen(navController: NavController,homeScreenViewmodel: HomeScreenViewModel) {

    var isSafe by remember { mutableStateOf(true) }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxSize()
            .background(
                color = if (isSafe) {
                    Color.Black
                } else {
                    Color(0xFF550505)
                }
            )
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp)

    ) {
        Header("SAFE CITY",isSafe)
        Spacer(modifier = Modifier.height(10.dp))


Column(
    horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFF043615))
        .padding(40.dp)
) {
   SecondHeader("SAFE CITY","WELCOME TO THE DASHBOARD",isSafe)

}

        Spacer(modifier = Modifier.height(20.dp))


        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            ActionItem(title = "Emergency Contacts",icon = Icons.Filled.AddAlert,
                onClick = {navController.navigate(Screens.EmergencyContact.route)})
            ActionItem(title = "Alerts",icon = Icons.Filled.AddAlert,
                onClick = {navController.navigate(BottomNavItem.Map.route)})
            ActionItem(title = "Settings",icon = Icons.Filled.Settings ,
                onClick = {navController.navigate(BottomNavItem.Map.route)})
            ActionItem(title = "Help",icon = Icons.Filled.Face,
                onClick = {navController.navigate(BottomNavItem.Map.route)})
        }
        Spacer(modifier = Modifier.height(20.dp))

        ButtonSOS("SOS", isSafe, onToggle = {isSafe = !isSafe},homeScreenViewmodel)

Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            CurrentStatus(isSafe)
            Spacer(modifier = Modifier.width(5.dp))
            AlertStatus(homeScreenViewmodel)
        }
    }
}




