package com.example.healthtracker.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.healthtracker.navigation.Screen
import com.example.healthtracker.screens.bloodpressure.BloodPressureTrackerScreen
import com.example.healthtracker.screens.welcome.WelcomeScreen


@Composable
fun app() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Welcome) }

    when (val screen = currentScreen) {
        Screen.Welcome -> WelcomeScreen(
            onNavigateToBloodPressure = {
                currentScreen = Screen.BloodPressureTracker
            }
        )
        Screen.BloodPressureTracker -> BloodPressureTrackerScreen(
            onBackClick = {
                currentScreen = Screen.Welcome
            }
        )
    }
}
