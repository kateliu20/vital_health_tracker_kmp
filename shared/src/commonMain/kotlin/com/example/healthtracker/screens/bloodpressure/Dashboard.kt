package com.example.healthtracker.screens.bloodpressure

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthtracker.HealthComponent
import com.example.healthtracker.data.BPReading
import com.example.healthtracker.screens.AppScaffold
import com.example.healthtracker.screens.medications.ReadOnlyAppointmentItem
import com.example.healthtracker.viewmodels.AppointmentViewModel
import com.example.healthtracker.viewmodels.BloodPressureViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
  component: HealthComponent,
  bloodPressureViewModel: BloodPressureViewModel,
  appointmentViewModel: AppointmentViewModel,
  onAddReading: () -> Unit,
) {
  val readings = bloodPressureViewModel.readings.value
  val scrollState = rememberScrollState()

  AppScaffold(component = component) { paddingValues ->
    Scaffold(
      topBar = {
        TopAppBar(
          title = { Text("BP Dashboard") },
          actions = {
            IconButton(onClick = onAddReading) {
              Icon(imageVector = Icons.Default.Add, contentDescription = "Add reading")
            }
          },
        )
      }
    ) { paddingValues ->
      Column(modifier = Modifier.fillMaxSize().padding(paddingValues).verticalScroll(scrollState)) {
        LazyRow(
          modifier = Modifier.fillMaxWidth().padding(8.dp),
          horizontalArrangement = Arrangement.Center,
        ) {
          item {
            val latest = bloodPressureViewModel.getLatestReading()
            StatCard(
              "Latest Reading",
              if (latest != null) "${latest.systolic}/${latest.diastolic}" else "No data",
            )
          }
        }

        // Blood Pressure Graph Card
        //            Card(
        //                modifier = Modifier
        //                    .fillMaxWidth()
        //                    .padding(8.dp)
        //            ) {
        //                Column(
        //                    modifier = Modifier
        //                        .padding(16.dp)
        //                ) {
        //                    Text(
        //                        "Blood Pressure Trend",
        //                        style = MaterialTheme.typography.titleMedium
        //                    )
        //                    Spacer(modifier = Modifier.height(8.dp))
        //
        //                    BloodPressureGraph(
        //                        readings = readings.map {
        //                            BloodPressureReading(
        //                                systolic = it.systolic,
        //                                diastolic = it.diastolic
        //                            )
        //                        },
        //                        modifier = Modifier
        //                            .fillMaxWidth()
        //                            .height(200.dp)
        //                    )
        //                }
        //            }

        // Blood Pressure Trend
        BloodPressureTrend()
        // Recent Readings Section
        RecentReadings(readings)

        UpcomingAppointments(appointmentViewModel)
      }
    }
  }
}

@Composable
fun BloodPressureTrend() {
  Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text("Blood Pressure Trend", style = MaterialTheme.typography.titleMedium)
      Spacer(modifier = Modifier.height(8.dp))

      SimpleChart()
    }
  }
}

@Composable
fun RecentReadings(readings: List<BPReading>) {
  Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text("Recent Readings", style = MaterialTheme.typography.titleMedium)
      Spacer(modifier = Modifier.height(8.dp))

      if (readings.isEmpty()) {
        Text("No readings recorded yet", style = MaterialTheme.typography.bodyMedium)
      } else {
        readings.take(5).forEach { reading ->
          ReadingItem(reading)
          HorizontalDivider()
        }
      }
    }
  }
}

@Composable
fun UpcomingAppointments(appointmentViewModel: AppointmentViewModel) {
  Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text("Upcoming Appointments", style = MaterialTheme.typography.titleMedium)
      Spacer(modifier = Modifier.height(8.dp))

      if (appointmentViewModel.upcomingAppointments.isEmpty()) {
        Text("No upcoming appointments", style = MaterialTheme.typography.bodyMedium)
      } else {
        appointmentViewModel.upcomingAppointments.take(3).forEach { appointment ->
          ReadOnlyAppointmentItem(appointment)
        }
      }
    }
  }
}

@Composable
private fun StatCard(title: String, value: String) {
  Card(modifier = Modifier.width(160.dp)) {
    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
      Text(text = title, style = MaterialTheme.typography.bodyMedium)
      Spacer(modifier = Modifier.height(4.dp))
      Text(text = value, style = MaterialTheme.typography.titleLarge)
    }
  }
}

@Composable
private fun ReadingItem(reading: BPReading) {
  Row(
    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Text("${reading.systolic}/${reading.diastolic}", style = MaterialTheme.typography.titleMedium)

    Text(
      "Today", // Replace with actual date formatting
      style = MaterialTheme.typography.bodyMedium,
    )
  }
}
