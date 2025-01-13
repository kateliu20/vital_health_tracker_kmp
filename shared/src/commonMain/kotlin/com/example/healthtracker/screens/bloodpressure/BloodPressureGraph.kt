package com.example.healthtracker.screens.bloodpressure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.koalaplot.core.ChartLayout
import io.github.koalaplot.core.Symbol
import io.github.koalaplot.core.line.LinePlot
import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
import io.github.koalaplot.core.xygraph.AxisModel
import io.github.koalaplot.core.xygraph.DefaultPoint
import io.github.koalaplot.core.xygraph.TickValues
import io.github.koalaplot.core.xygraph.XYGraph
import kotlin.math.max

@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
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
        val xRange =
            0f..(max(systolicReadings.size, diastolicReadings.size).toFloat() - 1).coerceAtLeast(5f)
        val yRange =
            40f..max(systolicReadings.maxOrNull() ?: 120f, diastolicReadings.maxOrNull() ?: 80f)
                .coerceAtLeast(200f)

        // Custom Axis Models because doesn't work on iOS
        val xAxisModel =
            object : AxisModel<Float> {
                override fun computeOffset(point: Float): Float {
                    return (point - xRange.start) / (xRange.endInclusive - xRange.start)
                }

                override fun computeTickValues(axisLength: Dp): TickValues<Float> {
                    val majorTicks = (xRange.start.toInt()..xRange.endInclusive.toInt()).map { it.toFloat() }
                    return object : TickValues<Float> {
                        override val majorTickValues = majorTicks
                        override val minorTickValues = emptyList<Float>() // No minor ticks for simplicity
                    }
                }
            }

        val yAxisModel =
            object : AxisModel<Float> {
                override fun computeOffset(point: Float): Float {
                    return (point - yRange.start) / (yRange.endInclusive - yRange.start)
                }

                override fun computeTickValues(axisLength: Dp): TickValues<Float> {
                    val majorTicks =
                        (yRange.start.toInt()..yRange.endInclusive.toInt() step 20).map { it.toFloat() }
                    return object : TickValues<Float> {
                        override val majorTickValues = majorTicks
                        override val minorTickValues = emptyList<Float>() // No minor ticks for simplicity
                    }
                }
            }

        XYGraph(xAxisModel = xAxisModel, yAxisModel = yAxisModel) {
            val systolicData =
                systolicReadings.mapIndexed { index, value -> DefaultPoint(index.toFloat(), value) }

            val diastolicData =
                diastolicReadings.mapIndexed { index, value -> DefaultPoint(index.toFloat(), value) }

            LinePlot(
                data = systolicData,
                // comment out linestyle because doesn't work on iOS
                // lineStyle = LineStyle(brush = SolidColor(Color.Red), strokeWidth = 2.dp),
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
                // comment out linestyle because doesn't work on iOS
                // lineStyle = LineStyle(brush = SolidColor(Color.Blue), strokeWidth = 2.dp),
                symbol = { point ->
                    Symbol(
                        shape = CircleShape,
                        fillBrush = SolidColor(Color.Blue),
                        modifier = Modifier.hoverableElement { Text("Diastolic: ${point.y.toInt()} mmHg") },
                    )
                },
            )

            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                LegendItem(color = Color.Red, label = "Systolic")
                Spacer(modifier = Modifier.width(16.dp))
                LegendItem(color = Color.Blue, label = "Diastolic")
            }
        }
    }
}

@Composable
fun LegendItem(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(12.dp).background(color, shape = CircleShape))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
    }
}
