package com.example.healthtracker.screens.bloodpressure

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.healthtracker.HealthComponent
import com.example.healthtracker.screens.AppScaffold
import com.example.healthtracker.viewmodels.BloodPressureViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BloodPressureTrackerScreen(
    component: HealthComponent,
    viewModel: BloodPressureViewModel,
    onBackClick: () -> Unit,
) {
    var systolicPressure by remember { mutableStateOf("") }
    var diastolicPressure by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    AppScaffold(component = component) { paddingValues ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Blood Pressure Tracker") },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Track Your Blood Pressure", style = MaterialTheme.typography.headlineMedium)

                OutlinedTextField(
                    value = systolicPressure,
                    onValueChange = { systolicPressure = it.filter { char -> char.isDigit() } },
                    label = { Text("Systolic Pressure (mmHg)") },
                    placeholder = { Text("Enter systolic pressure") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                )

                // Diastolic Pressure Input
                OutlinedTextField(
                    value = diastolicPressure,
                    onValueChange = {
                        // Limit input to digits only
                        diastolicPressure = it.filter { char -> char.isDigit() }
                    },
                    label = { Text("Diastolic Pressure (mmHg)") },
                    placeholder = { Text("Enter diastolic pressure") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                )

                Button(
                    onClick = {
                        val systolicValue = systolicPressure.toIntOrNull()
                        val diastolicValue = diastolicPressure.toIntOrNull()

                        if (systolicValue != null && diastolicValue != null) {
                            viewModel.addReading(systolicValue, diastolicValue)
                            // Clear the input fields after saving
                            systolicPressure = ""
                            diastolicPressure = ""
                            // Navigate back to dashboard
                            onBackClick()
                        } else {
                            showError = true
                            println("Please enter valid blood pressure values")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Save Blood Pressure Reading")
                }
            }
        }
    }
}
