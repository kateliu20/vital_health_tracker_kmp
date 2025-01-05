package com.example.healthtracker.screens.medications

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthtracker.data.Medication

@Composable
fun MedicationCard(
  medication: Medication,
  onEditMedication: (Medication) -> Unit,
  onDeleteMedication: (Medication) -> Unit,
) {
  Card(
    modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onEditMedication(medication) },
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(
        text = medication.name,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(text = "Dosage: ${medication.dosage}", style = MaterialTheme.typography.bodyMedium)
      Spacer(modifier = Modifier.height(8.dp))
      // Display notes
      if (medication.notes.isNotEmpty()) {
        Text(
          text = "Notes: ${medication.notes}",
          style = MaterialTheme.typography.bodyMedium,
          modifier = Modifier.padding(top = 4.dp)
        )
      }
      Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
        // Edit Button
        TextButton(onClick = { onEditMedication(medication) }) {
          Text("Edit", color = MaterialTheme.colorScheme.primary)
        }
        // Delete Button
        Spacer(modifier = Modifier.width(8.dp))
        TextButton(onClick = { onDeleteMedication(medication) }) {
          Text("Delete", color = MaterialTheme.colorScheme.error)
        }
      }
    }
  }
}
