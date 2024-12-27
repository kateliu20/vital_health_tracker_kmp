package com.example.healthtracker.screens.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreen(onNavigateToBloodPressure: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to Health Tracker",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Start your wellness journey",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(
            onClick = onNavigateToBloodPressure,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Track Blood Pressure")
        }
    }
}