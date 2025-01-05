package com.example.healthtracker.data

import health_tracker.shared.generated.resources.Res
import health_tracker.shared.generated.resources.bp
import health_tracker.shared.generated.resources.bp2
import health_tracker.shared.generated.resources.cold
import health_tracker.shared.generated.resources.virus
import org.jetbrains.compose.resources.DrawableResource

data class Article(val title: String, val imageRes: DrawableResource, val url: String)

val articles =
  listOf(
    Article(
      title = "Learn about High Blood Pressure",
      imageRes = Res.drawable.bp,
      url = "https://www.cdc.gov/high-blood-pressure/about/index.html",
    ),
    Article(
      title = "What your BP Reading Means",
      imageRes = Res.drawable.bp2,
      url = "https://www.mayoclinic.org/diseases-conditions/high-blood-pressure/in-depth/blood-pressure/art-20050982",
    ),
    Article(
      title = "Prep for the Winter",
      imageRes = Res.drawable.cold,
      url = "https://www.cdc.gov/winter-weather/safety/index.html",
    ),
    Article(
      title = "Prevent Norovius",
      imageRes = Res.drawable.virus,
      url = "https://www.cdc.gov/norovirus/prevention/index.html",
    ),
  )
