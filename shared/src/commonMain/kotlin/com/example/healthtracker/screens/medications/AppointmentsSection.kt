package com.example.healthtracker.screens.medications

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthtracker.viewmodels.AppointmentViewModel
import kotlinx.datetime.LocalDate

@Composable
fun AppointmentsSection(
  viewModel: AppointmentViewModel,
  selectedDate: LocalDate,
  onAddClick: () -> Unit = { /* Handle new appointment */ },
) {
  Text(
    "Appointments",
    style = MaterialTheme.typography.titleMedium,
    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
  )

  Column(
    modifier = Modifier.fillMaxWidth().padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("No appointments for $selectedDate", style = MaterialTheme.typography.bodyMedium)
    Button(onClick = onAddClick, modifier = Modifier.padding(top = 8.dp)) {
      Text("Add appointment")
    }
  }
}
