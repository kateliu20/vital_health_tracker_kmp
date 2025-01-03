package com.example.healthtracker

import androidx.compose.runtime.mutableStateOf
import com.example.healthtracker.navigation.Screen
import com.example.healthtracker.screens.bloodpressure.BloodPressureViewModel
import com.example.healthtracker.screens.medications.MedicationViewModel

class HealthComponent {
    val viewBloodPressureModel = BloodPressureViewModel()
    val viewMedicationModel = MedicationViewModel()
    val currentScreen = mutableStateOf<Screen>(Screen.WelcomeScreen)

    fun navigateTo(screen: Screen) {
        currentScreen.value = screen
    }

    companion object {
        fun create(): HealthComponent = HealthComponent()
    }
}