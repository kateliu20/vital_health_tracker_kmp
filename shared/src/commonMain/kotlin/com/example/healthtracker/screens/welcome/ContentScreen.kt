package com.example.healthtracker.screens.welcome

import androidx.compose.runtime.Composable
import com.example.healthtracker.HealthComponent
import com.example.healthtracker.navigation.Screen
import com.example.healthtracker.navigation.Screen.WebViewScreen
import com.example.healthtracker.screens.appointments.AppointmentScreen
import com.example.healthtracker.screens.bloodpressure.BloodPressureTrackerScreen
import com.example.healthtracker.screens.bloodpressure.DashboardScreen
import com.example.healthtracker.screens.medications.MedicineCabinetScreen

@Composable
fun ContentScreen(component: HealthComponent) {
    when (val screen = component.currentScreen.value) {
        Screen.WelcomeScreen ->
            WelcomeScreen(
                component = component,
                onNavigateToBloodPressure = { component.navigateTo(Screen.Dashboard) },
                onArticleClick = { articleUrl -> component.navigateTo(WebViewScreen(articleUrl)) },
            )

        Screen.Dashboard ->
            DashboardScreen(
                component = component,
                bloodPressureViewModel = component.viewBloodPressureModel,
                appointmentViewModel = component.appointmentViewModel,
                onAddReading = { component.navigateTo(Screen.BloodPressureTracker) },
            )

        Screen.BloodPressureTracker ->
            BloodPressureTrackerScreen(
                component = component,
                viewModel = component.viewBloodPressureModel,
                onBackClick = { component.navigateTo(Screen.Dashboard) },
            )

        Screen.AppointmentScreen ->
            AppointmentScreen(
                component = component,
                appointmentViewModel = component.appointmentViewModel,
            )

        Screen.MedicineCabinetScreen ->
            MedicineCabinetScreen(component = component)

        is WebViewScreen -> {
            WebViewScreen(url = screen.url, onBack = { component.navigateTo(Screen.WelcomeScreen) })
        }
    }
}
