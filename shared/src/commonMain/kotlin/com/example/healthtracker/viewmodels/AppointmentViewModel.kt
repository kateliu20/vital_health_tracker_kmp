package com.example.healthtracker.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.healthtracker.data.Appointment

class AppointmentViewModel {
  private val _appointments = mutableStateOf<List<Appointment>>(emptyList())
  val appointments: State<List<Appointment>> = _appointments

  fun deleteAppointment(appointment: Appointment) {
    _appointments.value = _appointments.value.filter { it.id != appointment.id }
  }
}
