package com.example.safecity.ui.theme.ui.home

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safecity.ui.theme.ui.utility.hasSmsPermission
import com.example.safecity.ui.theme.ui.utility.sendEmergencySms
import kotlinx.coroutines.delay


@Composable
fun ButtonSOS(title :String, safe : Boolean, onToggle : ()->Unit,homeScreenViewmodel: HomeScreenViewModel) {
    val selectedIndex = homeScreenViewmodel.selectedMessageEntity
    val context = LocalContext.current
    val emergencyContacts = homeScreenViewmodel.contacts.map { it.phoneNumber }
    val sentAlert = homeScreenViewmodel.sentAlert
    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                sendEmergencySms(
                    contacts = emergencyContacts,
                    message = homeScreenViewmodel.alertMessage.value
                )
            }else{

                Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    var triggerValue by remember { mutableStateOf(false) }

    // ⏱ Delayed action using LaunchedEffect
    LaunchedEffect(triggerValue) {
        if (triggerValue) {
            delay(1)
            onToggle()
            triggerValue = false
        }
    }

    Button(onClick = {
            onToggle()
            SendMessage(context, permissionLauncher, emergencyContacts,homeScreenViewmodel)
            triggerValue = true
        sentAlert.value = true
        }


        , modifier = Modifier.size(250.dp),
        colors = ButtonDefaults.buttonColors(
        containerColor = if (safe)
        {
            Color.Green
        }
        else {
            Color.Red
        }
    )
        , shape = CircleShape
    )
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center) {
        Text(text = title, fontSize = 100.sp, color = Color.Black)
        Text(text = "Tap for Emergency", fontSize = 20.sp, color = Color.Black)
    }
    }
}

fun SendMessage(context: Context,permissionLauncher: ActivityResultLauncher<String>,emergencyContacts: List<String>,homeScreenViewmodel: HomeScreenViewModel){
    val selectedIndex = homeScreenViewmodel.selectedMessageEntity.value

    if(hasSmsPermission(context)){
        sendEmergencySms(contacts = emergencyContacts, message = selectedIndex )
    }
    else{
        permissionLauncher.launch(Manifest.permission.SEND_SMS)
    }
}

