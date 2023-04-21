package com.example.myapplication.projetmobile.ui.detailsProject.composable

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

suspend fun convertFlowToList(flow: Flow<List<Task>>): List<Task> {
    return flow.first()
}
@Composable
fun TasksChart(tasks: Flow<List<Task>>) {
    val taskList = remember { mutableStateOf(emptyList<Task>()) }

    LaunchedEffect(tasks) {
        taskList.value = convertFlowToList(tasks)
    }

    val completedTasksCount = taskList.value.count { it.isCompleted }
    val totalTasksCount = taskList.value.size
    val completedTasksPercentage = remember(taskList.value) {
        if (totalTasksCount > 0) {
            (completedTasksCount.toFloat() / totalTasksCount.toFloat()) * 100f
        } else {
            0f
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(16.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val centerX = size.width / 2f
            val centerY = size.height / 2f
            val radius = size.minDimension / 2f - 32.dp.toPx()

            drawArc(
                color = Color(0xFFE5E5E5),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = true,
                topLeft = Offset(centerX - radius, centerY - radius),
                size = Size(radius * 2f, radius * 2f),
                style = Stroke(width = 32.dp.toPx())
            )

            drawArc(
                color = Color(0xFF61D8B9),
                startAngle = -90f,
                sweepAngle = completedTasksPercentage * 360f / 100f,
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
                    color = Color.Black.toArgb()
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