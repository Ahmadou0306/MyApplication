package com.example.myapplication.projetmobile.ui.home.componant

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.projetmobile.dataSource.models.Notification
import com.example.myapplication.projetmobile.dataSource.models.Project
import com.example.myapplication.projetmobile.viewsmodels.NotificationViewModel
import com.example.myapplication.projetmobile.viewsmodels.ProjectViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val colorPersonnel=0xFF1E88E5


@Composable
 fun ProjectCard(
    project: Project,
    onNavigation: (Project) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(vertical = 20.dp, horizontal = 5.dp)
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
        elevation = 10.dp,
        shape = RoundedCornerShape(5.dp),
        backgroundColor = Color.White,
        border = BorderStroke(2.dp, Color.LightGray)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = project.name,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = Color(color = colorPersonnel)
            )
            Spacer(modifier = Modifier.height(5.dp))
            if (isExpanded) {
                Text(
                    text = project.description,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = Modifier.height(1.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                    ,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Deadline : ${project.dueDate}",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = "Status : ${stateProject(project)}",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(onClick = { onNavigation(project)}, colors = ButtonDefaults.buttonColors( backgroundColor = Color(color = 0xFF1E88E5)) ,
                        shape= CutCornerShape(topStart = 7.dp, bottomEnd = 7.dp),
                        contentPadding = PaddingValues(10.dp)
                    )
                    {
                        Text(text = "More", color = Color(color = 0xFFFFFFFF))

                    }
                }
            } else {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@Composable
fun stateProject(project: Project): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-M-d")
    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val today = LocalDate.now().format(outputFormatter)
    val projectDate = LocalDate.parse(project.dueDateFin, inputFormatter)
    return  if (projectDate.format(outputFormatter) == today) " Terminer" else "En cour"
}