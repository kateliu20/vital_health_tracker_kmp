package com.example.healthtracker.screens.medications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import network.chaintech.kmp_date_time_picker.ui.timepicker.WheelTimePickerView
import network.chaintech.kmp_date_time_picker.utils.DateTimePickerView

@Composable
fun AddAppointmentDialog(
  selectedDate: LocalDate,
  onDismiss: () -> Unit,
  onConfirm: (title: String) -> Unit,
) {
  var title by remember { mutableStateOf("") }
  var showTimePicker by remember { mutableStateOf(false) }
  var selectedTime by remember { mutableStateOf<LocalTime?>(null) }

  AlertDialog(
    onDismissRequest = onDismiss,
    title = { Text("Add Appointment") },
    text = {
      Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth(),
      ) {
        OutlinedTextField(
          value = title,
          onValueChange = { title = it },
          label = { Text("Appointment name") },
          modifier = Modifier.fillMaxWidth(),
        )

        TextButton(
          onClick = { showTimePicker = true },
          modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
        ) {
          Text(
            selectedTime?.let { "Appointment time: $it" } ?: "Select Time",
            textDecoration = TextDecoration.Underline,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
          )
        }
      }
    },
    confirmButton = {
      TextButton(
        onClick = { onConfirm(title) },
        enabled = title.isNotBlank() && selectedTime != null,
      ) {
        Text("Add")
      }
    },
    dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
  )

  if (showTimePicker) {
    WheelTimePickerView(
      showTimePicker = true,
      rowCount = 5,
      height = 170.dp,
      dateTimePickerView = DateTimePickerView.BOTTOM_SHEET_VIEW,
      onDismiss = { showTimePicker = false },
      onDoneClick = { time ->
        selectedTime = time
        showTimePicker = false
      },
    )
  }
}
