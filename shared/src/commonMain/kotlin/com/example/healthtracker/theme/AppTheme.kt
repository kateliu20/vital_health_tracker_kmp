package com.example.healthtracker.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppTheme(content: @Composable () -> Unit) {
  MaterialTheme(
    colorScheme =
      lightColorScheme(
        primary = Color(0xFF4E79A7), // Soft blue
        secondary = Color(0xFF59A14F), // Muted green
        tertiary = Color(0xFFF28E2B), // Warm orange
        background = Color(0xFFF5F5F5), // Light gray background
        surface = Color.White,
        onPrimary = Color.White,
        onSecondary = Color.White,
        onBackground = Color.Black,
        onSurface = Color.Black,
      ),
    typography = Typography(),
    content = content,
  )
}
