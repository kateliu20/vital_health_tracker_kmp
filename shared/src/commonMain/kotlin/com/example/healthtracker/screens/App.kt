package com.example.healthtracker.screens

import androidx.compose.runtime.Composable
import com.example.healthtracker.HealthComponent
import com.example.healthtracker.screens.welcome.ContentScreen

@Composable
fun app(component: HealthComponent) {
  ContentScreen(component = component)
}
