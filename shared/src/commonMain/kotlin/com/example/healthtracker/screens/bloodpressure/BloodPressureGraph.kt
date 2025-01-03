// package com.example.healthtracker.screens.bloodpressure
//
// import androidx.compose.foundation.layout.*
// import androidx.compose.foundation.shape.CircleShape
// import androidx.compose.material3.MaterialTheme
// import androidx.compose.material3.Text
// import androidx.compose.runtime.Composable
// import androidx.compose.ui.Modifier
// import androidx.compose.ui.unit.dp
// import io.github.koalaplot.core.ChartLayout
// import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
// import io.github.koalaplot.core.xygraph.XYGraph
// import io.github.koalaplot.core.line.LinePlot
// import io.github.koalaplot.core.style.*
// import io.github.koalaplot.core.xygraph.FloatLinearAxisModel
// import io.github.koalaplot.core.xygraph.DefaultPoint
// import androidx.compose.ui.graphics.SolidColor
// import io.github.koalaplot.core.Symbol
//
// @OptIn(ExperimentalKoalaPlotApi::class)
// @Composable
// fun BloodPressureGraph(
//    readings: List<BloodPressureReading>,
//    modifier: Modifier = Modifier
// ) {
//    if (readings.isEmpty()) {
//        Text(
//            text = "No readings available",
//            style = MaterialTheme.typography.bodyMedium
//        )
//        return
//    }
//
//    ChartLayout(
//        modifier = modifier
//            .fillMaxWidth()
//            .height(300.dp)
//            .padding(16.dp)
//    ) {
//        XYGraph(
//            xAxisModel = FloatLinearAxisModel(0f..readings.size.toFloat()),
//            yAxisModel = FloatLinearAxisModel(0f..200f)
//        ) {
//            LinePlot(
//                data = readings.mapIndexed { index, reading ->
//                    DefaultPoint(index.toFloat(), reading.systolic.toFloat())
//                },
//                symbol = { point ->
//                    Symbol(
//                        shape = CircleShape,
//                        fillBrush = SolidColor(MaterialTheme.colorScheme.primary),
//                        modifier = Modifier.size(8.dp)
//                    )
//                }
//            )
//
//            LinePlot(
//                data = readings.mapIndexed { index, reading ->
//                    DefaultPoint(index.toFloat(), reading.diastolic.toFloat())
//                },
//                symbol = { point ->
//                    Symbol(
//                        shape = CircleShape,
//                        fillBrush = SolidColor(MaterialTheme.colorScheme.secondary),
//                        modifier = Modifier.size(8.dp)
//                    )
//                }
//            )
//        }
//    }
// }
//
// data class BloodPressureReading(
//    val systolic: Int,
//    val diastolic: Int
// )
