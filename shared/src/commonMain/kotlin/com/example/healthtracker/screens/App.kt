package com.example.healthtracker.screens

import androidx.compose.runtime.Composable
import com.example.healthtracker.HealthComponent
import com.example.healthtracker.screens.medications.WeeklyCalendarView
import com.example.healthtracker.screens.welcome.ContentScreen


@Composable
fun app(component: HealthComponent) {
//    WeeklyCalendarView(component = component)
    ContentScreen(component = component)
}
