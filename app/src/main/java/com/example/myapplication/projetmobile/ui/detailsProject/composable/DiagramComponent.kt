package com.example.myapplication.projetmobile.ui.detailsProject.composable

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.projetmobile.dataSource.models.Task
import com.example.myapplication.projetmobile.viewsmodels.TaskViewModel


@Composable
fun TasksChart(id:Int) {
    val viewModel = viewModel(TaskViewModel::class.java)
    val state by viewModel.state.collectAsState()
    viewModel.filterTasks(id)
    val tasks=state.tasksByProject


    val taskList = remember { mutableStateOf(emptyList<Task>()) }
    taskList.value = tasks


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
            .height(450.dp)
            .padding(25.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val centerX = size.width / 2f
            val centerY = size.height / 2f
            val radius = size.minDimension / 2f - 32.dp.toPx()

            drawArc(
                color = Color(0xFFFFD3D3),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = true,
                topLeft = Offset(centerX - radius, centerY - radius),
                size = Size(radius * 2f, radius * 2f),
                style = Stroke(width = 32.dp.toPx())
            )

            drawArc(
                color = Color(0xFF00FF48),
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
        Spacer(modifier = Modifier.height(2.dp))

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total Tasks",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = totalTasksCount.toString(),
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Completed Tasks",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = completedTasksCount.toString(),
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Completion Percentage",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "${completedTasksPercentage.toInt()}%",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun DiagramModal(showDialog: MutableState<Boolean>,selectedId:Int){
    // Modal
    if (showDialog.value) {
        Dialog(
            onDismissRequest = { showDialog.value = false },
            content = {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    elevation = 8.dp
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Evolution des tâches",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            IconButton(
                                onClick = { showDialog.value = false },
                                modifier = Modifier.size(48.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                            }
                        }
                        //DIAGRAM
                        TasksChart(id = selectedId)
                    }
                }
            }
        )
    }
}