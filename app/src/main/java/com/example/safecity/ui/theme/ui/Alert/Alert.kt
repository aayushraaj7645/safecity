package com.example.safecity.ui.theme.ui.Alert

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safecity.ui.theme.ui.home.HomeScreenViewModel

@Composable
fun AlertScreen(homeScreenViewModel: HomeScreenViewModel) {

var selectedIndex by remember { mutableIntStateOf(-1) }
    var msg by remember { mutableStateOf("") }
    var showForm by remember { mutableStateOf(false) }
    var selectedAlertMsg by remember { mutableStateOf(false) }

    fun checkedId(id: Int){
        selectedIndex = id
    }

    val messageList = homeScreenViewModel.messages

    LaunchedEffect(Unit) {
        homeScreenViewModel.getEmergencyAlertMessage()
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    )
    {
        Card(
            modifier = Modifier.fillMaxWidth().padding(25.dp).background(Color.Black),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Text(
                text = "Enter the message you want to send to the Emergency contacts when you click \n  SOS \n   button",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Center
            )
        }

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { showForm = !showForm }
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add Contact")
                }
            }
        ) { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF000000)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (showForm) {

                    Column(
                        modifier = Modifier.padding(16.dp).fillMaxSize()
                            .background(color = Color.Black),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Card(
                            modifier = Modifier.padding(36.dp)
                                .wrapContentSize(align = Alignment.Center),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                        ) {
                            OutlinedTextField(
                                value = msg,
                                onValueChange = { msg = it },
                                label = { Text("Enter your Alert message") },
                                placeholder = { Text("Enter message") },
                                modifier = Modifier.padding(16.dp)
                            )

                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    if (msg.isNotBlank()) {
                                        homeScreenViewModel.updateAlertMessage(msg)
                                        msg = ""
                                        showForm = false
                                    }
                                }
                            ) {
                                Text("Save")
                            }
                        }
                    }
                }

                LazyColumn(modifier = Modifier.background(Color(0xF21F201F)).fillMaxSize())
                {
                    items(messageList)
                    { message ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(5.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFA33E3E)),
                            elevation = CardDefaults.cardElevation(4.dp)
                        )
                        {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedIndex = message.id }
                            ) {
                                Checkbox(
                                    checked = selectedIndex == message.id,
                                    onCheckedChange = {
                                        selectedIndex = message.id
                                        homeScreenViewModel.getMessageById(message.id)
                                        checkedId(message.id)
                                    }
                                )

                                Text(
                                    text = "Alert Msg: - ${message.message}",
                                    fontSize = 30.sp,
                                    color = Color.White
                                )
                            }
                                Spacer(modifier = Modifier.height(10.dp))

                                Button(
                                    onClick = { homeScreenViewModel.deleteAlertMessageById(message.id) },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(
                                            0xFF660C0F
                                        )
                                    )
                                ) {
                                    Text(
                                        text = "Delete",
                                        modifier = Modifier.padding(5.dp),
                                        color = Color.White,
                                        fontSize = 20.sp
                                    )
                                }

                        }
                    }
                }
            }
        }
    }
}