package com.example.healthtracker.screens.medications

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthtracker.HealthComponent
import com.example.healthtracker.data.Medication
import com.example.healthtracker.screens.AppScaffold
import health_tracker.shared.generated.resources.Res
import health_tracker.shared.generated.resources.medicine
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineCabinetScreen(
    component: HealthComponent
) {
    var showAddMedicationDialog by remember { mutableStateOf(false) }
    var medicationToEdit by remember { mutableStateOf<Medication?>(null) }

    AppScaffold(component = component) { paddingValues ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Medicine Cabinet") },
                    actions = {
                        IconButton(onClick = { showAddMedicationDialog = true }) {
                            Icon(Icons.Default.Add, contentDescription = "Add Medication")
                        }
                    },
                )
            }
        ) { paddingValues ->
            val medications = component.viewMedicationModel.medications

            Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)) {
                if (medications.isEmpty()) {
                    Image(
                        painter = painterResource(Res.drawable.medicine),
                        contentDescription = "Medication",
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                    )
                    Text(
                        text = "No medications in the cabinet right now",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )
                    Button(
                        onClick = { showAddMedicationDialog = true },
                        modifier =
                            Modifier.padding(16.dp).align(Alignment.CenterHorizontally), // Center the button
                    ) {
                        Text("Add medication")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(medications) { medication ->
                            MedicationCard(
                                medication = medication,
                                onEditMedication = { selectedMedication ->
                                    medicationToEdit = selectedMedication // Open the edit dialog
                                },
                                onDeleteMedication = { selectedMedication ->
                                    // Handle deleting this medication
                                    component.viewMedicationModel.deleteMedication(selectedMedication)
                                    // println("Delete clicked for ${selectedMedication.name}")
                                },
                            )
                        }
                    }
                }
            }
        }
    }

    if (showAddMedicationDialog || medicationToEdit != null) {
        AddOrEditMedicationDialog(
            medication = medicationToEdit,
            onDismiss = {
                showAddMedicationDialog = false
                medicationToEdit = null
            },
            onSave = { updatedMedication ->
                if (medicationToEdit == null) {
                    component.viewMedicationModel.addMedication(updatedMedication)
                } else {
                    component.viewMedicationModel.updateMedication(medicationToEdit!!, updatedMedication)
                }
                showAddMedicationDialog = false
                medicationToEdit = null
            },
        )
    }
}
