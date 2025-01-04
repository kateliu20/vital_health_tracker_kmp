package com.example.healthtracker.data

import kotlinx.datetime.LocalDate

data class Appointment(val id: String, val title: String, val date: LocalDate, val time: String)
