package com.example.healthtracker.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.healthtracker.navigation.Screen
import com.example.healthtracker.screens.bloodpressure.BloodPressureTrackerScreen
import com.example.healthtracker.screens.bloodpressure.BloodPressureViewModel
import com.example.healthtracker.screens.bloodpressure.DashboardScreen
import com.example.healthtracker.screens.welcome.WelcomeScreen


@Composable
fun app() {
    val viewModel = remember { BloodPressureViewModel() }
    var currentScreen by remember { mutableStateOf<Screen>(Screen.WelcomeScreen) }

    when (val screen = currentScreen) {
        Screen.WelcomeScreen -> WelcomeScreen(
            onNavigateToBloodPressure = {
                currentScreen = Screen.Dashboard
            }
        )
        Screen.Dashboard -> DashboardScreen(
            viewModel = viewModel,
            onAddReading = {
                currentScreen = Screen.BloodPressureTracker
            }
        )
        Screen.BloodPressureTracker -> BloodPressureTrackerScreen(
            viewModel = viewModel,
            onBackClick = {
                currentScreen = Screen.Dashboard
            }
        )
    }
}
