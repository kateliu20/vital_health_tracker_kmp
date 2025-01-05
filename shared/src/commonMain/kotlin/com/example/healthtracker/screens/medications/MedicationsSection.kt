// package com.example.healthtracker.screens.medications
//
// import androidx.compose.foundation.layout.Arrangement
// import androidx.compose.foundation.layout.Box
// import androidx.compose.foundation.layout.fillMaxWidth
// import androidx.compose.foundation.layout.height
// import androidx.compose.foundation.layout.padding
// import androidx.compose.foundation.lazy.LazyColumn
// import androidx.compose.foundation.lazy.items
// import androidx.compose.material3.MaterialTheme
// import androidx.compose.material3.Text
// import androidx.compose.runtime.Composable
// import androidx.compose.runtime.getValue
// import androidx.compose.ui.Alignment
// import androidx.compose.ui.Modifier
// import androidx.compose.ui.unit.dp
// import com.example.healthtracker.viewmodels.MedicationViewModel
// import kotlinx.datetime.LocalDate
//
// @Composable
// fun MedicationsSection(viewModel: MedicationViewModel, selectedDate: LocalDate) {
//  Text(
//    "Medications",
//    style = MaterialTheme.typography.titleMedium,
//    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
//  )
//
//  if (viewModel.medications.isNotEmpty()) {
//    LazyColumn(
//      modifier = Modifier.fillMaxWidth().height(200.dp).padding(horizontal = 16.dp),
//      verticalArrangement = Arrangement.spacedBy(8.dp),
//    ) {
//      items(viewModel.medications) { medication ->
//        MedicationItem(
//          medication = medication,
//          onEditClick = { /* TODO: Implement edit medication */ },
//          onDeleteClick = { viewModel.deleteMedication(medication) },
//        )
//      }
//    }
//  } else {
//    Box(modifier = Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
//      Text("No medications", style = MaterialTheme.typography.bodyMedium)
//    }
//  }
// }
