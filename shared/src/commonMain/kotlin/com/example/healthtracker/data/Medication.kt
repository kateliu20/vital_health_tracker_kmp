package com.example.healthtracker.data

data class Medication(val id: String, val name: String, val dosage: String)

enum class MedicationType {
  PRESCRIPTION,
  SUPPLEMENT,
}
