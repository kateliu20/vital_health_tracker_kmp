package com.example.healthtracker.screens.welcome

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import com.example.healthtracker.HealthComponent
import com.example.healthtracker.navigation.NavItem
import com.example.healthtracker.navigation.Screen
import com.example.healthtracker.screens.bloodpressure.BloodPressureTrackerScreen
import com.example.healthtracker.screens.bloodpressure.DashboardScreen

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    component: HealthComponent,
    onScreenChanged: (Screen) -> Unit
){
    val navItemList = listOf(
        NavItem("Home", Icons.Default.Home, Screen.WelcomeScreen),
        NavItem("Dashboard", Icons.Default.Menu, Screen.Dashboard),
        NavItem("Add Data", Icons.Default.Add, Screen.BloodPressureTracker)
    )

    NavigationBar {
        navItemList.forEach { navItem ->
            NavigationBarItem(
                selected = component.currentScreen.value == navItem.screen,
                onClick = {
                    onScreenChanged(navItem.screen)
                },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.label
                    )
                },
                label = {
                    Text(text = navItem.label)
                }
            )
        }
    }
}

@Composable
fun ContentScreen(
    component: HealthComponent,
    modifier: Modifier = Modifier
) {
    when (component.currentScreen.value) {
        Screen.WelcomeScreen -> WelcomeScreen(
            component = component,
            onNavigateToBloodPressure = {
                component.navigateTo(Screen.Dashboard)
            }
        )
        Screen.Dashboard -> DashboardScreen(
            component = component,
            viewModel = component.viewModel,
            onAddReading = {
                component.navigateTo(Screen.BloodPressureTracker)
            }
        )
        Screen.BloodPressureTracker -> BloodPressureTrackerScreen(
            component = component,
            viewModel = component.viewModel,
            onBackClick = {
                component.navigateTo(Screen.Dashboard)
            }
        )
    }
}