package com.example.healthtracker.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthtracker.HealthComponent
import com.example.healthtracker.screens.AppScaffold
import health_tracker.shared.generated.resources.Res
import health_tracker.shared.generated.resources.vital
import org.jetbrains.compose.resources.painterResource

@Composable
fun WelcomeScreen(component: HealthComponent, onNavigateToBloodPressure: () -> Unit) {
  AppScaffold(component = component) { innerPadding ->
    Column(
      modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Text("Welcome to")
      Image(
        painter = painterResource(Res.drawable.vital),
        contentDescription = "Vital Logo",
        modifier = Modifier.fillMaxWidth(),
      )
      Button(onClick = onNavigateToBloodPressure, modifier = Modifier.padding(top = 8.dp)) {
        Text("Go to Health Dashboard")
      }
    }
  }
}
