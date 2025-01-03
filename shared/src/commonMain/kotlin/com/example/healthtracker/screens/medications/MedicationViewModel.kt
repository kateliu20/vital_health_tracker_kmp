package com.example.healthtracker.screens.medications

import androidx.compose.runtime.mutableStateListOf
import com.example.healthtracker.data.Medication

class MedicationViewModel {
    private val _medications = mutableStateListOf<Medication>()
    val medications: List<Medication> = _medications

    fun addMedication(medication: Medication) {
        _medications.add(medication)
    }

    fun deleteMedication(medication: Medication) {
        _medications.remove(medication)
    }

    fun updateMedication(oldMedication: Medication, newMedication: Medication) {
        val index = _medications.indexOf(oldMedication)
        if (index != -1) {
            _medications[index] = newMedication
        }
    }
}
