package com.example.myapplication.projetmobile.ui.home.componant

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

//@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("NewApi")
//private val today = LocalDate.now()
val daysOfWeek = listOf("Dim", "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam")
val months = listOf("Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre")

@Composable fun Calendar(
    startDateStr: String,
    endDateStr: String,
    modifier: Modifier = Modifier,
    onDateSelected: (LocalDate) -> Unit = {}
) {
    val formatter = DateTimeFormatter.ISO_LOCAL_DATE
//    val selectedDate = remember { mutableStateOf(today) }
    val startDate = remember { mutableStateOf(LocalDate.parse(startDateStr, formatter)) } // Définition de la première date
    val endDate = remember { mutableStateOf(LocalDate.parse(endDateStr, formatter)) } // Définition de la dernière date
    val selectedDate = remember { mutableStateOf(startDate.value) }

    Column(modifier = modifier.padding(16.dp)) {
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
                modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally)
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
                    val dayOfMonth = (weekOfMonth - 1) * 7 + dayOfWeek - firstDayOfMonth.dayOfWeek.value + 1
                    if (dayOfMonth < 1 || dayOfMonth > daysInMonth) {
                        Spacer(modifier = Modifier.weight(1f))
                    } else {
                        val date = LocalDate.of(selectedDate.value.year, selectedDate.value.month, dayOfMonth)
                        val isInRange = date in startDate.value..endDate.value
                        val backgroundColor = if (isInRange) Color(color = 0xFF1E88E5) else Color.Transparent
                        val startdaybackgroundColor = if (date == startDate.value) Color(color = 0xFF1BFF24) else Color.Transparent
                        val enddaybackgroundColor = if (date == endDate.value) Color(color = 0xFFFF0000) else Color.Transparent
                        val textColor = if (isInRange) Color.White else Color.Unspecified
                        Text(
                            text = dayOfMonth.toString(),
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier
                                .clickable { selectedDate.value = date; onDateSelected(date) }
                                .weight(1f)
                                .background(color = backgroundColor)
                                .background(color = startdaybackgroundColor)
                                .background(color = enddaybackgroundColor),
                            color = textColor
                        )
                    }
                }
            }
        }

    }
}
