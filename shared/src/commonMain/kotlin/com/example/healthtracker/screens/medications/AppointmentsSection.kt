package com.example.healthtracker.screens.medications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthtracker.data.Appointment
import com.example.healthtracker.viewmodels.AppointmentViewModel
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun AppointmentsSection(
  viewModel: AppointmentViewModel,
  selectedDate: LocalDate,
  onAddClick: () -> Unit = { /* Handle new appointment */ },
) {
  var showAddDialog by remember { mutableStateOf(false) }
  val appointmentsForDay = viewModel.appointments.value
  Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
    Text(
      "Appointments",
      style = MaterialTheme.typography.titleMedium,
      modifier = Modifier.padding(bottom = 8.dp),
    )

    if (appointmentsForDay.isNotEmpty()) {
      LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(appointmentsForDay) { appointment ->
          AppointmentItem(
            appointment = appointment,
            onEditClick = { /* TODO */ },
            onDeleteClick = { viewModel.deleteAppointment(appointment) },
          )
        }
      }
    } else {
      Text(
        "No appointments for $selectedDate",
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
    }

    Button(
      onClick = { showAddDialog = true },
      modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 16.dp),
    ) {
      Text("Add appointment")
    }

    if (showAddDialog) {
      AddAppointmentDialog(
        onDismiss = { showAddDialog = false },
        onConfirm = { title, time ->
          viewModel.addAppointment(
            title = title,
            time = formatAppointmentTime(time)
          )
          showAddDialog = false
        },
      )
    }
  }
}

@Composable
fun AppointmentItem(appointment: Appointment, onEditClick: () -> Unit, onDeleteClick: () -> Unit) {
  Card(
    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
  ) {
    Row(
      modifier = Modifier.padding(16.dp).fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Column(modifier = Modifier.weight(1f)) {
        Text(text = appointment.title, style = MaterialTheme.typography.titleMedium)
        Text(
          text = appointment.time,
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
      }

      Row {
//        IconButton(onClick = onEditClick) { Icon(Icons.Default.Edit, "Edit") }
        IconButton(onClick = onDeleteClick) { Icon(Icons.Default.Delete, "Delete") }
      }
    }
  }
}

