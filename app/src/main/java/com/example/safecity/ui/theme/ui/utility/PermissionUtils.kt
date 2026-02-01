package com.example.safecity.ui.theme.ui.utility

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import android.telephony.SmsManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

fun hasSmsPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.SEND_SMS
    ) == PackageManager.PERMISSION_GRANTED
}



fun sendEmergencySms(
    contacts: List<String>,
    message: String
) {

    val smsManager = SmsManager.getDefault()

    contacts.forEach { phone ->
        smsManager.sendTextMessage(
            phone,
            null,
            message,
            null,
            null
        )
    }
}


