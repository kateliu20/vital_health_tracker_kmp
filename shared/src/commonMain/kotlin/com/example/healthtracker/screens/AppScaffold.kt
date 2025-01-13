package com.example.healthtracker.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.healthtracker.HealthComponent
import com.example.healthtracker.screens.welcome.BottomNavigationBar

@Composable
fun AppScaffold(component: HealthComponent, content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                component = component,
                onScreenChanged = { screen -> component.navigateTo(screen) },
            )
        }
    ) { innerPadding ->
        content(innerPadding)
    }
}
