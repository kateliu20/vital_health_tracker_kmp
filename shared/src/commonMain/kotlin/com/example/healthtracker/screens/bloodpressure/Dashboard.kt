package com.example.healthtracker.screens.bloodpressure

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthtracker.HealthComponent
import com.example.healthtracker.data.BPReading
import com.example.healthtracker.screens.AppScaffold
import com.example.healthtracker.screens.appointments.ReadOnlyAppointmentItem
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
  val scrollState = rememberScrollState()

  AppScaffold(component = component) { paddingValues ->
    Scaffold(
      topBar = {
        TopAppBar(
          title = { Text("Your Health Dashboard") }
          //          actions = {
          //            IconButton(onClick = onAddReading) {
          //              Icon(imageVector = Icons.Default.Add, contentDescription = "Add reading")
          //            }
          //          },
        )
      }
    ) { paddingValues ->
      Column(modifier = Modifier.fillMaxSize().padding(paddingValues).verticalScroll(scrollState)) {
        // Latest Reading Card
        LazyRow(
          modifier = Modifier.fillMaxWidth().padding(8.dp),
          horizontalArrangement = Arrangement.Center,
        ) {
          item {
            val latest = bloodPressureViewModel.getLatestReading()
            StatCard(
              title = "Latest Blood Pressure Reading",
              value = if (latest != null) "${latest.systolic}/${latest.diastolic}" else "No data",
              onAddClick = { onAddReading() },
            )
          }
        }

        // Blood Pressure Trend
        BloodPressureTrend(bloodPressureViewModel)

        // Upcoming Appointments
        UpcomingAppointments(appointmentViewModel)
      }
    }
  }
}

@Composable
fun BloodPressureTrend(bloodPressureViewModel: BloodPressureViewModel) {
  Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text("Recent Blood Pressure Trends", style = MaterialTheme.typography.titleMedium)
      Spacer(modifier = Modifier.height(8.dp))

      val systolicReadings = bloodPressureViewModel.getSystolicReadings()
      val diastolicReadings = bloodPressureViewModel.getDiastolicReadings()

      if (systolicReadings.isNotEmpty() && diastolicReadings.isNotEmpty()) {
        BloodPressurePlot(title = "Blood Pressure Trend", systolicReadings, diastolicReadings)
      } else {
        // Show a message when no data is available
        Text(
          text = "No blood pressure readings available",
          style = MaterialTheme.typography.bodyLarge,
          modifier = Modifier.padding(16.dp),
        )
      }
    }
  }
}

@Composable
fun RecentReadings(readings: List<BPReading>) {
  Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text("Recent Blood Pressure Readings", style = MaterialTheme.typography.titleMedium)
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
private fun StatCard(title: String, value: String, onAddClick: (() -> Unit)? = null) {
  Card(
    modifier = Modifier.padding(8.dp),
    shape = MaterialTheme.shapes.medium,
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
  ) {
    Column(
      modifier = Modifier.padding(16.dp).fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Text(
        text = title,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom = 8.dp),
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = value,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
      )

      if (onAddClick != null) {
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onAddClick, modifier = Modifier.fillMaxWidth(0.6f)) { Text("Add Entry") }
      }
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

    Text("Today", style = MaterialTheme.typography.bodyMedium)
  }
}
