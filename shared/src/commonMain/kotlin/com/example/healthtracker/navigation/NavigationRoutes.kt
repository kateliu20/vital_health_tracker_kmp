package com.example.healthtracker.navigation

sealed class Screen {
    data object Welcome : Screen()
    data object BloodPressureTracker : Screen()
}