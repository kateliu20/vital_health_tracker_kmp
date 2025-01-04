package com.example.healthtracker.data

data class Medication(
  val name: String,
  val dosage: String,
  val type: MedicationType,
  val frequency: MedicationFrequency,
)

enum class MedicationType {
  PRESCRIPTION,
  SUPPLEMENT,
}
