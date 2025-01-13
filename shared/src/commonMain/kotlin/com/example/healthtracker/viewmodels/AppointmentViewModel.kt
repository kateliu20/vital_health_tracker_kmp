package com.example.healthtracker.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.benasher44.uuid.uuid4
import com.example.healthtracker.data.Appointment
import kotlinx.datetime.LocalDate
import network.chaintech.kmp_date_time_picker.utils.now

class AppointmentViewModel {
    private val _appointments = mutableStateOf<List<Appointment>>(emptyList())
    val appointments: State<List<Appointment>> = _appointments

    val upcomingAppointments: List<Appointment>
        get() =
            _appointments.value
                .filter { it.date >= LocalDate.now() } // Filter future appointments
                .sortedBy { it.date } // Sort by date
                .take(3)

    fun addAppointment(title: String, date: LocalDate, time: String) {
        val newAppointment =
            Appointment(id = uuid4().toString(), title = title, date = date, time = time)

        _appointments.value += newAppointment
    }

    fun deleteAppointment(appointment: Appointment) {
        _appointments.value = _appointments.value.filter { it.id != appointment.id }
    }
}
