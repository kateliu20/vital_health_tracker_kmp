package com.example.healthtracker

import androidx.compose.runtime.mutableStateOf
import com.example.healthtracker.navigation.Screen
import com.example.healthtracker.viewmodels.AppointmentViewModel
import com.example.healthtracker.viewmodels.BloodPressureViewModel
import com.example.healthtracker.viewmodels.MedicationViewModel

class HealthComponent {
    val viewBloodPressureModel = BloodPressureViewModel()
    val viewMedicationModel = MedicationViewModel()
    val appointmentViewModel = AppointmentViewModel()
    val currentScreen = mutableStateOf<Screen>(Screen.WelcomeScreen)

    fun navigateTo(screen: Screen) {
        currentScreen.value = screen
    }

    companion object {
        fun create(): HealthComponent = HealthComponent()
    }
}
