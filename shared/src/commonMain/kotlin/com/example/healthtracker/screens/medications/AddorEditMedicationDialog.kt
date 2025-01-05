package com.example.healthtracker.screens.medications

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.benasher44.uuid.uuid4
import com.example.healthtracker.data.Medication

@Composable
fun AddOrEditMedicationDialog(
    medication: Medication?, // null for adding, non-null for editing
    onDismiss: () -> Unit,
    onSave: (Medication) -> Unit
) {
    var name by remember { mutableStateOf(medication?.name ?: "") }
    var dosage by remember { mutableStateOf(medication?.dosage?.split(" ")?.firstOrNull() ?: "") }
    var selectedUnit by remember { mutableStateOf(medication?.dosage?.split(" ")?.lastOrNull() ?: "pills") }
    var notes by remember { mutableStateOf(medication?.notes ?: "") }

    var unitExpanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (medication == null) "Add Medication" else "Edit Medication") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                // Name input
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Medication Name") },
                    modifier = Modifier.fillMaxWidth(),
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    OutlinedTextField(
                        value = dosage,
                        onValueChange = { dosage = it.filter { char -> char.isDigit() } },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = { Text("Dosage") },
                    )
                    Box(modifier = Modifier.width(96.dp)) {
                        OutlinedTextField(
                            value = selectedUnit,
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth(),
                            trailingIcon = {
                                Icon(
                                    imageVector =
                                        if (unitExpanded) Icons.Default.KeyboardArrowUp
                                        else Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Select unit",
                                )
                            },
                        )

                        // Clickable overlay for dropdown
                        Box(modifier = Modifier.matchParentSize().clickable { unitExpanded = !unitExpanded })

                        DropdownMenu(expanded = unitExpanded, onDismissRequest = { unitExpanded = false }) {
                            listOf("pills", "mg", "ml", "g").forEach { unit ->
                                DropdownMenuItem(
                                    text = { Text(unit) },
                                    onClick = {
                                        selectedUnit = unit
                                        unitExpanded = false
                                    },
                                )
                            }
                        }
                    }
                }
                // Notes section
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes") },
                    modifier = Modifier.fillMaxWidth().height(150.dp),
                    placeholder = { Text("Add any additional notes here") },
                    maxLines = 4
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val updatedMedication = Medication(
                        id = medication?.id ?: uuid4().toString(),
                        name = name,
                        dosage = "$dosage $selectedUnit",
                        notes = notes
                    )
                    onSave(updatedMedication)
                    onDismiss()
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
    )
}

