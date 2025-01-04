package com.example.healthtracker.data

import kotlinx.datetime.LocalTime

data class Appointment(
  val id: String,
  val title: String,
  val time: String,
)
