package com.example.safecity.ui.theme.ui

sealed class Screens(val route: String) {
    data object EmergencyContact : Screens("emergency_contacts")

}