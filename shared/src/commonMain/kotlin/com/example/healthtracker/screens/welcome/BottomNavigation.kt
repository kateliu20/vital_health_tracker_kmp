package com.example.healthtracker.screens.welcome

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.healthtracker.HealthComponent
import com.example.healthtracker.navigation.NavItem
import com.example.healthtracker.navigation.Screen

@Composable
fun BottomNavigationBar(component: HealthComponent, onScreenChanged: (Screen) -> Unit) {
  val navItemList =
    listOf(
      NavItem("Home", Icons.Default.Home, Screen.WelcomeScreen),
      NavItem("Dashboard", Icons.Default.Menu, Screen.Dashboard),
      NavItem("Add Data", Icons.Default.Add, Screen.BloodPressureTracker),
      NavItem("Calendar", Icons.Outlined.DateRange, Screen.MedicationScreen),
    )

  NavigationBar {
    navItemList.forEach { navItem ->
      NavigationBarItem(
        selected = component.currentScreen.value == navItem.screen,
        onClick = { onScreenChanged(navItem.screen) },
        icon = { Icon(imageVector = navItem.icon, contentDescription = navItem.label) },
        label = { Text(text = navItem.label) },
        colors =
          NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            unselectedTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
          ),
      )
    }
  }
}
