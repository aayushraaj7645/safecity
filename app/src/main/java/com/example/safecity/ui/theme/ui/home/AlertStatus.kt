package com.example.safecity.ui.theme.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlertStatus(homeScreenViewModel: HomeScreenViewModel ) {
    val sentAlert = homeScreenViewModel.sentAlert
    val emergencyContactName = homeScreenViewModel.contacts.map { it.name }
    Card(modifier = Modifier.size(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)) {
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)){
            Text("Alert Status")
            if(!sentAlert.value){
                Spacer(modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(color = Color.Green))
                Text("No Alerts",fontSize = 20.sp)}
            else{
                Spacer(modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(color = Color.Red))
                for(name in emergencyContactName){
                Text(text ="Msg sent to - $name",fontSize = 20.sp )
                }
            }

        }
    }

}