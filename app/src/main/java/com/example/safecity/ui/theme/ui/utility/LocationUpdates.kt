package com.example.safecity.ui.theme.ui.utility

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import com.google.android.gms.location.LocationRequest

import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng

lateinit var locationCallback: LocationCallback


fun startLocationUpdates(
    context: Context,
    onLocationUpdate: (LatLng) -> Unit
): FusedLocationProviderClient {

    val fusedClient = LocationServices.getFusedLocationProviderClient(context)

    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        return fusedClient
    }

    val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY,
        2000L // every 3 seconds
    ).setMinUpdateDistanceMeters(3f)
        .build()

     locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            result.lastLocation?.let {
                onLocationUpdate(LatLng(it.latitude, it.longitude))
            }
        }
    }

    fusedClient.requestLocationUpdates(
        locationRequest,
        locationCallback,
        Looper.getMainLooper()
    )

    return fusedClient
}

