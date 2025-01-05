package com.example.healthtracker.screens.appointments

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.healthtracker.HealthComponent
import com.example.healthtracker.screens.AppScaffold
import com.example.healthtracker.viewmodels.AppointmentViewModel

@Composable
fun AppointmentScreen(component: HealthComponent, appointmentViewModel: AppointmentViewModel) {
  AppScaffold(component = component) { paddingValues ->
    Scaffold { topBarPadding ->
      Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        WeeklyCalendarView(appointmentViewModel)
      }
    }
  }
}
