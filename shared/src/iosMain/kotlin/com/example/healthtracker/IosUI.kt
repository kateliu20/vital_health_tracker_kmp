package com.example.healthtracker

import androidx.compose.ui.window.ComposeUIViewController
import com.example.healthtracker.screens.app
import platform.UIKit.UIViewController

fun makeUIViewController(component: HealthComponent): UIViewController =
    ComposeUIViewController(
        configure = {
            enforceStrictPlistSanityCheck = false
        }
    ) {
        app(component)
    }