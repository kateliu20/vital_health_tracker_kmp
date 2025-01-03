package com.example.healthtracker.data

import kotlinx.datetime.LocalTime

data class Medication(
  val name: String,
  val dosage: String,
  val schedule: List<LocalTime>,
  val type: MedicationType,
)

enum class MedicationType {
  PRESCRIPTION,
  SUPPLEMENT,
}
