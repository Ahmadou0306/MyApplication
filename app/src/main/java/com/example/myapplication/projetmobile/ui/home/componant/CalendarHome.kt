package com.example.myapplication.projetmobile.ui.home.componant
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.projetmobile.ui.detailsProject.composable.ShowSubProjects
import com.example.myapplication.projetmobile.viewsmodels.ProjectViewModel
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.util.Calendar

@Composable
fun CalendarView(

) {
    val viewModel = viewModel(ProjectViewModel::class.java)
    val state by viewModel.state.collectAsState()
    val calendar = Calendar.getInstance()

    LazyColumn {
        // Affiche le titre du mois
        itemsIndexed(items = Month.values()) { index, month ->
            Text(
                text = month.name,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(top = if (index == 0) 16.dp else 32.dp)
                    .fillMaxWidth()
            )

            // Affiche chaque jour du mois
            val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            for (dayOfMonth in 1..daysInMonth) {
                val date = LocalDate.of(calendar.get(Calendar.YEAR), month, dayOfMonth)

                // Vérifie si la date correspond à un projet
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                val matchingProjects = state.projectList.filter {
                    LocalDate.parse(it.dueDate, formatter) == date
                }

                if (matchingProjects.isNotEmpty()) {
                    Box(
                        Modifier
                            .padding(4.dp)
                            .background(Color.Green)
                            .size(24.dp)
                    )
                } else {
                    Box(Modifier.size(24.dp))
                }
            }
        }
    }
}

@Composable
fun CalendarModal(showDialog: MutableState<Boolean>){
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
                                text = "List  SubProjects",
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
                        //list Member
                        CalendarView()
                    }
                }
            }
        )
    }
}