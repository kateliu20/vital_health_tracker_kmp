package com.example.healthtracker.data

import health_tracker.shared.generated.resources.Res
import health_tracker.shared.generated.resources.blood_pressure
import org.jetbrains.compose.resources.DrawableResource

data class Article(
    val title: String,
    val imageRes: DrawableResource,
    val url: String)

val articles = listOf(
    Article(
        title = "Learn about High Blood Pressure",
        imageRes = Res.drawable.blood_pressure,
        url = "https://www.cdc.gov/high-blood-pressure/about/index.html"
    ),
    Article(
        title = "Learn about High Blood Pressure",
        imageRes = Res.drawable.blood_pressure,
        url = "https://www.cdc.gov/high-blood-pressure/about/index.html"
    ),
    Article(
        title = "Learn about High Blood Pressure",
        imageRes = Res.drawable.blood_pressure,
        url = "https://www.cdc.gov/high-blood-pressure/about/index.html"
    )
)
