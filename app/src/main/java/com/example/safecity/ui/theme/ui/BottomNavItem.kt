package com.example.safecity.ui.theme.ui



import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn

import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Home : BottomNavItem(
        route = "home",
        label = "Home",
        icon = Icons.Filled.Home
    )

    data object Map : BottomNavItem(
        route = "map",
        label = "Map",
        icon = Icons.Filled.LocationOn
    )

    data object Alerts : BottomNavItem(
        route = "alerts",
        label = "Alerts",
        icon = Icons.Filled.Notifications
    )

    data object Profile : BottomNavItem(
        route = "profile",
        label = "Profile",
        icon = Icons.Filled.Person
    )




    companion object {
        val items = listOf(Home, Map,Alerts, Profile)
    }
}
