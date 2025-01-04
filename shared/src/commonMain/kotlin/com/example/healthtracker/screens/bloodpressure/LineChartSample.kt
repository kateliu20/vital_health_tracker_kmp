package com.example.healthtracker.screens.bloodpressure

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import io.github.koalaplot.core.ChartLayout
import io.github.koalaplot.core.Symbol
import io.github.koalaplot.core.line.LinePlot
import io.github.koalaplot.core.style.LineStyle
import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
import io.github.koalaplot.core.xygraph.DefaultPoint
import io.github.koalaplot.core.xygraph.XYGraph
import io.github.koalaplot.core.xygraph.rememberFloatLinearAxisModel

@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
@Suppress("MagicNumber")
fun BloodPressurePlot(
  title: String,
  systolicReadings: List<Float>,
  diastolicReadings: List<Float>,
) {
  // Safety check
  if (systolicReadings.isEmpty() || diastolicReadings.isEmpty()) {
    return
  }

  ChartLayout(
    modifier = Modifier.padding(16.dp).fillMaxWidth().height(300.dp),
    title = { ChartTitle(title) },
  ) {
    XYGraph(
      xAxisModel =
        rememberFloatLinearAxisModel(
          range = 0f..maxOf(5f, (systolicReadings.size - 1).toFloat()),
          minimumMajorTickSpacing = 50.dp,
        ),
      yAxisModel =
        rememberFloatLinearAxisModel(
          range = 40f..200f, // Typical BP range
          minimumMajorTickSpacing = 20.dp,
        ),
    ) {
      // Create points for systolic line
      val systolicData =
        systolicReadings.mapIndexed { index, value -> DefaultPoint(index.toFloat(), value) }

      // Create points for diastolic line
      val diastolicData =
        diastolicReadings.mapIndexed { index, value -> DefaultPoint(index.toFloat(), value) }

      // Systolic line
      LinePlot(
        data = systolicData,
//        lineStyle = LineStyle(brush = SolidColor(Color.Red), strokeWidth = 2.dp),
        symbol = { point ->
          Symbol(
            shape = CircleShape,
            fillBrush = SolidColor(Color.Red),
            modifier = Modifier.hoverableElement { Text("Systolic: ${point.y.toInt()} mmHg") },
          )
        },
      )

      // Diastolic line
      LinePlot(
        data = diastolicData,
//        lineStyle = LineStyle(brush = SolidColor(Color.Blue), strokeWidth = 2.dp),
        symbol = { point ->
          Symbol(
            shape = CircleShape,
            fillBrush = SolidColor(Color.Blue),
            modifier = Modifier.hoverableElement { Text("Diastolic: ${point.y.toInt()} mmHg") },
          )
        },
      )
    }
  }
}

@Composable
fun PreviewBloodPressurePlot() {
  // Sample data for preview
  val systolicReadings = listOf(120f, 122f, 118f, 121f, 119f)
  val diastolicReadings = listOf(80f, 82f, 79f, 81f, 80f)

  BloodPressurePlot(
    title = "Blood Pressure over time",
    systolicReadings = systolicReadings,
    diastolicReadings = diastolicReadings,
  )
}
