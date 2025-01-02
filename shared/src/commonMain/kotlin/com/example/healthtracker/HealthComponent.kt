package com.example.healthtracker

import androidx.compose.runtime.mutableStateOf
import com.example.healthtracker.navigation.Screen
import com.example.healthtracker.screens.bloodpressure.BloodPressureViewModel

class HealthComponent {
    val viewModel = BloodPressureViewModel()
    val currentScreen = mutableStateOf<Screen>(Screen.WelcomeScreen)

    fun navigateTo(screen: Screen) {
        currentScreen.value = screen
    }

    companion object {
        fun create(): HealthComponent = HealthComponent()
    }
}