package com.example.myapplication.projetmobile
import android.graphics.Paint
import android.graphics.Typeface
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.CalendarView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme


class DetailsProject :ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface( color = MaterialTheme.colors.background) {
                }
            }
        }
    }
}

@Composable
fun Add(){
    Text(text = "Mansour")
}

@Composable
fun Detail(){
    Surface {
        Column {
            Header()
            Box {
                ActionIcons()
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 0.dp, horizontal = 16.dp)
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
                ) {
                    Text(text = "add", color = Color.White)
                }
            }
            Box {
                TaskCard3Preview()
            }

        }
    }
}




@Composable
fun Header(){
    Surface(
        color = Color.Blue,
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(horizontal = 3.dp, vertical = 3.dp)
            .clip(
                CutCornerShape(
                    topStart = 30.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 35.dp,
                )
            )
            .border(
                width = 5.dp,
                color = Color.White,
                shape = CutCornerShape(
                    topStart = 30.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 35.dp,
                )
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Créer un Text pour le titre du projet
            Text(
                text = "Titre du projet",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // Créer un Text pour le nom du chef de projet
            Text(
                text = "Chef de projet: John Doe",
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ActionIcons(){

        Surface {
            Row(
                horizontalArrangement = Arrangement.spacedBy(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, 20.dp, 0.dp, 20.dp)
            ) {


                Box{
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(70.dp)
                                    .padding(12.dp)
                                    .border(
                                        2.dp,
                                        MaterialTheme.colors.secondary,
                                        CircleShape
                                    )
                            )
                        }

                        Text(
                            text = "Add Sous-projet",
                            fontSize = 15.sp,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                        )
                    }
                }



                Box{
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(70.dp)
                                    .padding(12.dp)
                                    .border(
                                        2.dp,
                                        MaterialTheme.colors.secondary,
                                        CircleShape
                                    )
                            )
                        }

                        Text(
                            text = "Add tâche",
                            fontSize = 15.sp,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                        )
                    }
                }


                Box{
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(70.dp)
                                    .padding(12.dp)
                                    .border(
                                        2.dp,
                                        MaterialTheme.colors.secondary,
                                        CircleShape
                                    )
                            )
                        }

                        Text(
                            text = "Diagram",
                            fontSize = 15.sp,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                        )
                    }
                }
            }

        }

    }





@Composable
fun TasksChart(tasks: List<Task3>) {
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


data class Task3(
    val name: String,
    val description: String,
    val dueDate: String,
    var isCompleted: Boolean,
    var assignedMembers: String,
)

val taskList = listOf(
    Task3("FRERE","Fils de mon pere","23/01/2023",true,"Ma mere"),
    Task3("PAPA","Fils de mon pere","23/01/2023",true,"Ma mere"),
    Task3("Mansour","Fils de mon pere","23/01/2023",true,"Ma mere"),
    Task3("PERE","Fils de mon pere","23/01/2023",false,"Ma mere"),
    Task3("Mansour","Fils de mon pere","23/01/2023",false,"Ma mere"),
    )

@Preview(showBackground = true)
@Composable
fun PreviewDiagram(){
        TasksChart(tasks = taskList)
}


@Composable
fun TaskCard3(task: Task3) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // Titre de la tâche
            Text(
                text = task.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Description de la tâche
            Text(
                text = task.description,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Date d'échéance
            Text(
                text = "Date d'échéance : ${task.dueDate}",
                fontSize = 14.sp,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Icônes pour supprimer la tâche, ajouter un membre et valider la tâche
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Supprimer la tâche",
                        tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    )
                }

                IconButton(
                    onClick = {},
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Ajouter un membre à la tâche",
                        tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    )
                }

                IconButton(
                    onClick = {},
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Valider la tâche",
                        tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskCard3Preview() {
    LazyColumn {
        items(taskList) { task ->
            TaskCard3(task = task)
        }
    }
}

@Composable
fun CalendarCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // Titre de la carte
            Text(
                text = "Calendrier",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Calendrier
            val calendar = Calendar.getInstance()
            val today = calendar.timeInMillis
            var maxDate = calendar.apply { add(Calendar.MONTH, 6) }.timeInMillis

            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(vertical = 16.dp),
                factory = { context ->
                    CalendarView(context).apply {
                        firstDayOfWeek = Calendar.MONDAY
                        minDate = today
                        maxDate = maxDate


                        setOnDateChangeListener { _, year, month, dayOfMonth ->
                            // Gérer la sélection de la date
                        }

                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CalendarPreview(){
    CalendarCard()
}