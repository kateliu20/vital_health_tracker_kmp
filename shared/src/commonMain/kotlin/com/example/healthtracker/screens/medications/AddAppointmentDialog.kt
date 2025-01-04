package com.example.healthtracker.screens.medications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import network.chaintech.kmp_date_time_picker.ui.timepicker.WheelTimePickerDialog

@Composable
fun AddAppointmentDialog(
  selectedDate: LocalDate,
  onDismiss: () -> Unit,
  onConfirm: (title: String) -> Unit,
) {
  var title by remember { mutableStateOf("") }
  var showDatePicker by remember { mutableStateOf(false) }

  AlertDialog(
    onDismissRequest = onDismiss,
    title = { Text("Add Appointment") },
    text = {
      Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })

        WheelTimePickerDialog(height = 200.dp)
      }
    },
    confirmButton = { TextButton(onClick = { onConfirm(title) }) { Text("Add") } },
    dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
  )
}
