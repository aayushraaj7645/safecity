

package com.example.safecity.ui.theme.ui


import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.safecity.ui.theme.ui.utility.locationCallback
import com.example.safecity.ui.theme.ui.utility.startLocationUpdates
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen() {

    val context = LocalContext.current
    var permissionGranted by remember { mutableStateOf(false) }
    var userLocation by remember { mutableStateOf<LatLng?>(null) }

    LocationPermission {
        permissionGranted = true
    }

    if (permissionGranted) {

        val cameraPositionState = rememberCameraPositionState()

        // start & stop live location
        DisposableEffect(Unit) {
            val fusedClient = startLocationUpdates(context) { location ->
                userLocation = location
            }

            onDispose {
                fusedClient.removeLocationUpdates(locationCallback)
            }
        }

        LaunchedEffect(userLocation) {
            userLocation?.let {
                cameraPositionState.position =
                    CameraPosition.fromLatLngZoom(it, 18f)
            }
        }

        val properties = MapProperties(
            isMyLocationEnabled = true
        )

        val uiSettings = MapUiSettings(
            myLocationButtonEnabled = true
        )

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = properties,
            uiSettings = uiSettings
        )
    }
}



@Composable
fun LocationPermission(onGranted: () -> Unit) {

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            onGranted()
        }
    }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            onGranted() // already granted
        } else {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
}

