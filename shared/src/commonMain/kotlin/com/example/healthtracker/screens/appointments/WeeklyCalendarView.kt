package com.example.healthtracker.screens.appointments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.healthtracker.viewmodels.AppointmentViewModel
import kotlinx.datetime.*

@Composable
fun WeeklyCalendarView(appointmentViewModel: AppointmentViewModel) {
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    var selectedDate by remember { mutableStateOf(today) }

    Column(modifier = Modifier.fillMaxSize()) {
        WeekHeader(selectedDate, today) { newDate -> selectedDate = newDate }
        AppointmentsSection(viewModel = appointmentViewModel, selectedDate = selectedDate)
    }
}

@Composable
fun WeekHeader(currentDate: LocalDate, today: LocalDate, onDateSelected: (LocalDate) -> Unit) {
    val weekStart = currentDate.minus((currentDate.dayOfWeek.ordinal + 1) % 7, DateTimeUnit.DAY)
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(onClick = { onDateSelected(weekStart.minus(1, DateTimeUnit.WEEK)) }) {
                Text("←", style = MaterialTheme.typography.headlineLarge)
            }
            Text("${weekStart.month} ${weekStart.year}", style = MaterialTheme.typography.titleMedium)
            IconButton(onClick = { onDateSelected(weekStart.plus(1, DateTimeUnit.WEEK)) }) {
                Text("→", style = MaterialTheme.typography.headlineLarge)
            }
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            val days = (0..6).map { weekStart.plus(it, DateTimeUnit.DAY) }
            items(days) { day -> DayHeader(day, day == currentDate, onDateSelected) }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (currentDate != today) {
                TextButton(onClick = { onDateSelected(today) }) {
                    Text(
                        "Today",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}

@Composable
fun DayHeader(date: LocalDate, isSelected: Boolean, onDateSelected: (LocalDate) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(40.dp).clickable { onDateSelected(date) },
    ) {
        Text(
            text = date.dayOfWeek.name.take(3),
            style = MaterialTheme.typography.bodySmall,
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Unspecified,
        )
        Text(
            text = date.dayOfMonth.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Unspecified,
        )
    }
}
