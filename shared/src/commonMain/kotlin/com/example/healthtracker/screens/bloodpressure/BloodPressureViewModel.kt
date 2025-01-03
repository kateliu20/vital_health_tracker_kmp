package com.example.healthtracker.screens.bloodpressure

import com.example.healthtracker.data.BPReading
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.datetime.Clock

class BloodPressureViewModel {
    private val _readings = mutableStateOf<List<BPReading>>(emptyList())
    val readings: State<List<BPReading>> = _readings

    fun addReading(systolic: Int, diastolic: Int) {
        val newReading = BPReading(
            systolic = systolic,
            diastolic = diastolic,
            timestamp = Clock.System.now().toEpochMilliseconds()
        )
        _readings.value += newReading
    }

    fun getLatestReading(): BPReading? = readings.value.maxByOrNull { it.timestamp }
}