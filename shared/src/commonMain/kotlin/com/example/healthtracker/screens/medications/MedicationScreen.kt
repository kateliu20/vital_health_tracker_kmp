package com.example.healthtracker.screens.medications

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthtracker.HealthComponent
import com.example.healthtracker.screens.AppScaffold
import kotlinx.datetime.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationScreen(
    component: HealthComponent,
    viewModel: MedicationViewModel
) {
    var showAddDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date) }

    AppScaffold(component = component) { paddingValues ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Medications") },
                    actions = {
                        IconButton(onClick = { showAddDialog = true }) {
                            Icon(Icons.Default.Add, contentDescription = "Add Medication")
                        }
                    }
                )
            }
        ) { topBarPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(topBarPadding)
            ) {
                WeeklyCalendarView(
                )

                // Medications for the selected day
                val medicationsForDay = viewModel.medications // Show all medications for now
                if (medicationsForDay.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        items(medicationsForDay) { medication ->
                            MedicationItem(
                                medication = medication,
                                onEditClick = { /* Open Edit Medication Dialog */ },
                                onDeleteClick = { viewModel.deleteMedication(medication) }
                            )
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "No medications added",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        AddMedicationDialog(
            onDismiss = { showAddDialog = false },
            onSave = { medication ->
                viewModel.addMedication(medication)
                showAddDialog = false
            }
        )
    }
}
