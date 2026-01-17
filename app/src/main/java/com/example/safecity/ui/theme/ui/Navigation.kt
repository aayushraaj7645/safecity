package com.example.safecity.ui.theme.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.safecity.ui.theme.ui.Alert.AlertScreen
import com.example.safecity.ui.theme.ui.home.EmergencyContacts
import com.example.safecity.ui.theme.ui.home.HomeScreen
import com.example.safecity.ui.theme.ui.home.HomeScreenViewModel

@Composable
fun SafeCityApp(homeScreenViewmodel: HomeScreenViewModel) {
    val navController = rememberNavController()
    val items = BottomNavItem.items




    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                // Avoid building backstack of bottom nav screens
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )
                        },
                        label = {
                            Text(text = item.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) { HomeScreen(navController,homeScreenViewmodel) }
            composable(BottomNavItem.Map.route) { MapScreen()}
            composable(BottomNavItem.Alerts.route) { AlertScreen(homeScreenViewmodel) }
            composable(BottomNavItem.Profile.route) { ProfileScreen() }
            composable(Screens.EmergencyContact.route) {
                EmergencyContacts(navController, homeScreenViewmodel)
        }
    }
}}