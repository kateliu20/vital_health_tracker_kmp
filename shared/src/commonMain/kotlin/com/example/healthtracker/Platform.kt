package com.example.healthtracker
interface Platform {
  val name: String
}

expect fun getPlatform(): Platform
