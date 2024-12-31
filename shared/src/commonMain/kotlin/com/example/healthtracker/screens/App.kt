package com.example.healthtracker.screens

import androidx.compose.runtime.Composable
import com.example.healthtracker.HealthComponent
import com.example.healthtracker.navigation.Screen
import com.example.healthtracker.screens.bloodpressure.BloodPressureTrackerScreen
import com.example.healthtracker.screens.bloodpressure.DashboardScreen
import com.example.healthtracker.screens.welcome.WelcomeScreen


@Composable
fun app(component: HealthComponent) {
    when (val screen = component.currentScreen.value) {
        Screen.WelcomeScreen -> WelcomeScreen(
            onNavigateToBloodPressure = {
                component.navigateTo(Screen.Dashboard)
            }
        )
        Screen.Dashboard -> DashboardScreen(
            viewModel = component.viewModel,
            onAddReading = {
                component.navigateTo(Screen.BloodPressureTracker)
            }
        )
        Screen.BloodPressureTracker -> BloodPressureTrackerScreen(
            viewModel = component.viewModel,
            onBackClick = {
                component.navigateTo(Screen.Dashboard)
            }
        )
    }
}

