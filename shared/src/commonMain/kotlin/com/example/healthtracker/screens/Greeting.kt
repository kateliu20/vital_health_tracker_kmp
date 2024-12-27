package com.example.healthtracker.screens

import androidx.compose.runtime.Composable
import com.example.healthtracker.screens.welcome.WelcomeScreen

class Greeting {
    @Composable
    fun greet() {
        WelcomeScreen(
            onNavigateToBloodPressure = {
                // Navigation will be handled by MainActivity
            }
        )
    }
}