package com.example.healthtracker.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.healthtracker.data.BPReading
import kotlinx.datetime.Clock

class BloodPressureViewModel {
    private val _readings = mutableStateOf<List<BPReading>>(emptyList())
    val readings: State<List<BPReading>> = _readings

    fun addReading(systolic: Int, diastolic: Int) {
        val newReading =
            BPReading(
                systolic = systolic,
                diastolic = diastolic,
                timestamp = Clock.System.now().toEpochMilliseconds(),
            )
        _readings.value += newReading
    }

    fun getSystolicReadings(): List<Float> = readings.value.map { it.systolic.toFloat() }

    fun getDiastolicReadings(): List<Float> = readings.value.map { it.diastolic.toFloat() }

    fun getLatestReading(): BPReading? = readings.value.maxByOrNull { it.timestamp }
}
