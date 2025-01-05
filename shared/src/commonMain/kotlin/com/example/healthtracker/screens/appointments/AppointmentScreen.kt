package com.example.healthtracker.screens.appointments

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.healthtracker.HealthComponent
import com.example.healthtracker.screens.AppScaffold
import com.example.healthtracker.viewmodels.AppointmentViewModel
import com.example.healthtracker.viewmodels.MedicationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(
  component: HealthComponent,
  medicationViewModel: MedicationViewModel,
  appointmentViewModel: AppointmentViewModel,
) {
  var showAddDialog by remember { mutableStateOf(false) }

  AppScaffold(component = component) { paddingValues ->
    Scaffold(
      // topBar = {
      //     TopAppBar(
      //         title = { Text("Medications") },
      //         actions = {
      //             IconButton(onClick = { showAddDialog = true }) {
      //                 Icon(Icons.Default.Add, contentDescription = "Add Medication")
      //             }
      //         },
      //     )
      // }
    ) { topBarPadding ->
      // Adjust padding to avoid double padding
      Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        WeeklyCalendarView(medicationViewModel, appointmentViewModel)
      }
    }
  }

  //  if (showAddDialog) {
  //    AddMedicationDialog(
  //      onDismiss = { showAddDialog = false },
  //      onSave = { medication ->
  //        medicationViewModel.addMedication(medication)
  //        showAddDialog = false
  //      },
  //    )
  //  }
}
