package com.example.healthtracker.navigation

sealed class Screen {
  data object WelcomeScreen : Screen()

  data object Dashboard : Screen()

  data object BloodPressureTracker : Screen()

  data object MedicationScreen : Screen()
}
