package com.example.myapplication.projetmobile.ui.composable

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.projetmobile.dataSource.models.Task


@Composable
fun TasksChart(tasks: List<Task>) {
    val completedTasksCount = tasks.count { it.isCompleted }
    val totalTasksCount = tasks.size
    val completedTasksPercentage = if (totalTasksCount > 0) {
        (completedTasksCount.toFloat() / totalTasksCount.toFloat()) * 100f
    } else {
        0f
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val centerX = size.width / 2f
            val centerY = size.height / 2f
            val radius = size.minDimension / 2f - 32.dp.toPx()

            drawArc(
                color = Color.Gray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = true,
                topLeft = Offset(centerX - radius, centerY - radius),
                size = Size(radius * 2f, radius * 2f),
                style = Stroke(width = 32.dp.toPx())
            )

            drawArc(
                color = Color.Blue,
                startAngle = -90f,
                sweepAngle = (completedTasksPercentage / 100f) * 360f,
                useCenter = true,
                topLeft = Offset(centerX - radius, centerY - radius),
                size = Size(radius * 2f, radius * 2f),
                style = Stroke(width = 32.dp.toPx())
            )

            drawCircle(
                color = Color.White,
                radius = radius - 32.dp.toPx(),
                center = Offset(centerX, centerY),
                style = Stroke(width = 32.dp.toPx())
            )

            drawIntoCanvas {
                val textPaint = Paint().apply {
                    color = Color.Blue.toArgb()
                    textSize = 30.sp.toPx()
                    typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                }

                val text = "${completedTasksPercentage.toInt()}%"
                val textWidth = textPaint.measureText(text)
                it.nativeCanvas.drawText(
                    text,
                    centerX - textWidth / 2f,
                    centerY + textPaint.textSize / 2f,
                    textPaint
                )
            }
        }
    }
}


