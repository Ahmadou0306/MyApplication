package com.example.myapplication.projetmobile.ui.home.componant

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.projetmobile.dataSource.models.Project
import com.example.myapplication.projetmobile.ui.componant.EmptyContentCalendar
import com.example.myapplication.projetmobile.viewsmodels.ProjectViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)


@Composable
fun CalendarModal(showDialog: MutableState<Boolean>){
    val viewModel = viewModel(ProjectViewModel::class.java)
    val state by viewModel.state.collectAsState()
    val datesString= mutableListOf<LocalDate>()
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-M-d")
    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
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
                                text = "Calendrier",
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
                        //Calendar
                        if(state.projectList.isNotEmpty()){
                            state.projectList.forEach { project ->
                                datesString.add( LocalDate.parse(outputFormatter.format(LocalDate.parse(project.dueDate, inputFormatter))))
                            }
                            val dateProject = datesString.sorted()
                            Calendrier(dateProject)
                        }else{
                            EmptyContentCalendar()
                        }
                        }
                    }
                }
        )
    }
}

@Composable fun Calendrier(datess: List<LocalDate>, modifier: Modifier = Modifier) {
    var showDialog = remember { mutableStateOf(false) }
    val startDate = remember { mutableStateOf(datess[0]) }
    val selectedDate = remember { mutableStateOf(startDate.value) }

    Column(modifier = modifier.padding(15.dp)) {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    selectedDate.value = selectedDate.value.plusYears(1)
                },
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Next Year")
            }

            Text(
                text = "${selectedDate.value.year}",
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )

            IconButton(
                onClick = {
                    selectedDate.value = selectedDate.value.minusYears(1)
                },
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Previous Year")
            }
        }
        // Header with month and year
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        )  {

            // Month and year
            Text(
                text = months[selectedDate.value.monthValue - 1],
                style = MaterialTheme.typography.h6,
                modifier = Modifier.weight(1f)
            )
            // Button to navigate to previous month
            IconButton(onClick = {
                selectedDate.value = selectedDate.value.minusMonths(1)
            }) {
                Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = "Previous Month")
            }
            // Button to navigate to next month
            IconButton(onClick = {
                selectedDate.value = selectedDate.value.plusMonths(1)
            }) {
                Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "Next Month")
            }
        }
        // Days of week header
        Row {
            for (day in daysOfWeek) {
                Text(
                    text = day,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        // Days of the month
        val firstDayOfMonth = LocalDate.of(selectedDate.value.year, selectedDate.value.month, 1)
        val daysInMonth = selectedDate.value.month.length(selectedDate.value.isLeapYear)

        for (weekOfMonth in 1..6) {
            Row (modifier = Modifier.fillMaxWidth()) {
                for (dayOfWeek in 1..7) {

                    val dayOfMonth = (weekOfMonth - 1) * 7 + dayOfWeek - firstDayOfMonth.dayOfWeek.value
                    if (dayOfMonth < 1 || dayOfMonth > daysInMonth) {
                        Spacer(modifier = Modifier.weight(1f))
                    } else {
                        val date = LocalDate.of(selectedDate.value.year, selectedDate.value.month, dayOfMonth)
                        val isInRange = date in datess
                        val backgroundColor = if (isInRange) Color(color = com.example.myapplication.projetmobile.ui.detailsProject.colorPersonnel) else Color.Transparent
                        val textColor = if (isInRange) Color.White else Color.Unspecified



                        val shape = if (isInRange) CircleShape else RectangleShape

                        Text(
                            text = dayOfMonth.toString(),
                            style = MaterialTheme.typography.h6.copy(
                                color = textColor,
                                fontWeight = if (isInRange) FontWeight.Light else FontWeight.Normal,
                                fontSize = if (isInRange) 15.sp else 16.sp,
                            ),
                            modifier = Modifier
                                .clickable {
                                    selectedDate.value = date
                                    if (isInRange) showDialog.value = true
                                }
                                .weight(1f)
                                .background(color = backgroundColor, shape = shape)
                                .border(
                                    width = 1.dp,
                                    color = if (isInRange) Color(com.example.myapplication.projetmobile.ui.detailsProject.colorPersonnel) else Color.Transparent,
                                    shape = shape
                                )
                                .padding(5.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
    selectedDate(showDialog,selectedDate.value)
}

@Composable
fun selectedDate(showDialog: MutableState<Boolean>, value: LocalDate){
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
                                .padding(15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "project pour $value",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Light
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
                        rechercheProjectByDate(value)
                    }
                }
            }
        )
    }
}
@Composable
fun projectCalendar(project: Project) {
    Card(
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "",
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "${project.name}",
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Composable
fun rechercheProjectByDate(date: LocalDate) {
    val viewModel = viewModel(ProjectViewModel::class.java)
    val state by viewModel.state.collectAsState()
    LazyColumn {
        if (state.projectList.isNotEmpty()){
            items(state.projectList) { project ->
                val projectDate = LocalDate.parse(project.dueDate, DateTimeFormatter.ofPattern("yyyy-M-d"))
                if (date == projectDate) {
                    projectCalendar(project)
                }
            }
        }
    }
}