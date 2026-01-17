package com.example.safecity.ui.theme.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController


@Composable
fun EmergencyContacts(navController: NavController,homeScreenViewmodel: HomeScreenViewModel) {

    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var showForm by remember { mutableStateOf(false) }
    val contactsList =homeScreenViewmodel.contacts

    LaunchedEffect(Unit) {
        homeScreenViewmodel.getContacts()
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
                Card {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Name") },
                            placeholder = { Text("Enter Name") }
                        )

                        OutlinedTextField(
                            value = number,
                            onValueChange = { number = it },
                            label = { Text("Number") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            placeholder = { Text("Enter Number") }
                        )

                        Button(
                            onClick = {
                                if (name.isNotBlank() && number.isNotBlank()) {
                                   homeScreenViewmodel.saveContacts(name,number)
                                    name = ""
                                    number = ""
                                    showForm = false
                                }
                            }
                        ) {
                            Text("Save")
                        }
                    }
                }
            }

            LazyColumn(modifier = Modifier.background( Color(0xF21F201F)).fillMaxSize())
            {
                items(contactsList)
                { entry->
                        Card(modifier = Modifier.fillMaxWidth().padding(5.dp)
                            ,colors = CardDefaults.cardColors(containerColor = Color(0xFFA33E3E)),
                            elevation = CardDefaults.cardElevation(4.dp))
                        {
                        Text(text = "Name : - ${entry.name}", fontSize = 30.sp, color = Color.White)
                            Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Phone NO. - ${entry.phoneNumber}", fontSize = 30.sp, color = Color.White)
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(onClick = { homeScreenViewmodel.deleteContact(entry.id) }, modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(
                                    0xFF660C0F
                                )
                                )) {
                                Text(text = "Delete", modifier = Modifier.padding(5.dp), color = Color.White, fontSize = 20.sp)
                            }
                        }
                    }
                }
            }
        }
    }





