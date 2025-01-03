package com.example.healthtracker.screens.welcome

import androidx.compose.runtime.Composable
import com.example.healthtracker.HealthComponent
import com.example.healthtracker.navigation.Screen
import com.example.healthtracker.screens.bloodpressure.BloodPressureTrackerScreen
import com.example.healthtracker.screens.bloodpressure.DashboardScreen
import com.example.healthtracker.screens.medications.MedicationScreen

@Composable
fun ContentScreen(component: HealthComponent) {
  when (component.currentScreen.value) {
    Screen.WelcomeScreen ->
      WelcomeScreen(
        component = component,
        onNavigateToBloodPressure = { component.navigateTo(Screen.Dashboard) },
      )
    Screen.Dashboard ->
      DashboardScreen(
        component = component,
        viewModel = component.viewBloodPressureModel,
        onAddReading = { component.navigateTo(Screen.BloodPressureTracker) },
      )
    Screen.BloodPressureTracker ->
      BloodPressureTrackerScreen(
        component = component,
        viewModel = component.viewBloodPressureModel,
        onBackClick = { component.navigateTo(Screen.Dashboard) },
      )
    Screen.MedicationScreen ->
      MedicationScreen(
        component = component,
        medicationViewModel = component.viewMedicationModel,
        appointmentViewModel = component.appointmentViewModel
        )
  }
}
