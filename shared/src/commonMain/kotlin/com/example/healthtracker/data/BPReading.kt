package com.example.healthtracker.data

data class BPReading(
    val systolic: Int,
    val diastolic: Int,
    val timestamp: Long,
    val notes: String? = null
)