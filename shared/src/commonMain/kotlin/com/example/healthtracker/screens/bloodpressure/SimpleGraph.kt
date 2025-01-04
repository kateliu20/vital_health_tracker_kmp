package com.example.healthtracker.screens.bloodpressure

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SimpleChart(data1: List<Float>, data2: List<Float>) {
  Canvas(modifier = Modifier.fillMaxSize().padding(16.dp).height(200.dp)) {
    val canvasWidth = size.width
    val canvasHeight = size.height

    // Draw the first line
    val points1 =
      data1.mapIndexed { index, value ->
        Offset(
          x = index.toFloat() / (data1.size - 1) * canvasWidth,
          y = canvasHeight - (value / data1.max()!! * canvasHeight),
        )
      }

    drawLine(
      color = Color(0xFF4285F4), // Google blue
      start = points1.first(),
      end = points1.last(),
      strokeWidth = 2.dp.toPx(),
    )

    // Draw the first line data points
    points1.forEach { point ->
      drawCircle(color = Color(0xFF4285F4), radius = 4.dp.toPx(), center = point)
    }

    // Draw the second line
    val points2 =
      data2.mapIndexed { index, value ->
        Offset(
          x = index.toFloat() / (data2.size - 1) * canvasWidth,
          y = canvasHeight - (value / data2.max()!! * canvasHeight),
        )
      }

    drawLine(
      color = Color(0xFFDB4437), // Google red
      start = points2.first(),
      end = points2.last(),
      strokeWidth = 2.dp.toPx(),
    )

    // Draw the second line data points
    points2.forEach { point ->
      drawCircle(color = Color(0xFFDB4437), radius = 4.dp.toPx(), center = point)
    }
  }
}
