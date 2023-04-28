package com.example.myapplication.projetmobile.ui.home.componant

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.projetmobile.dataSource.models.Notification
import com.example.myapplication.projetmobile.dataSource.models.Project
import com.example.myapplication.projetmobile.ui.componant.EmptyContent
import com.example.myapplication.projetmobile.viewsmodels.NotificationViewModel
import com.example.myapplication.projetmobile.viewsmodels.ProjectViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
@Composable
fun countNotificationsToday(projectList: List<Project>): Int {
    val viewModel = viewModel(NotificationViewModel::class.java)
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-M-d")
    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val today = LocalDate.now().format(outputFormatter)
    viewModel.deleteAllNotification()
    var count = 0
    for (project in projectList) {
        val projectDate = LocalDate.parse(project.dueDateFin, inputFormatter)
        if (projectDate.format(outputFormatter) == today) {
            count++
            viewModel.addNotification(
                Notification(
                    0,
                    project.id,
                    "Project ${project.name} Complete!"
                )
            )
        }
    }
    return count
}
@Composable
fun listNotification():Int{
    val viewModel = viewModel(ProjectViewModel::class.java)
    val state by viewModel.state.collectAsState()
   return countNotificationsToday(state.projectList)
}

@Composable
fun MessageModal(showDialog: MutableState<Boolean>){
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
                           Row(
                               verticalAlignment=Alignment.CenterVertically
                           ){
                               Icon(
                                   imageVector = Icons.Default.MailOutline,
                                   contentDescription = null,
                                   tint = Color.Black
                               )
                               Spacer(modifier = Modifier.width(10.dp))
                               Text(
                                   text = "Messages",
                                   fontSize = 18.sp,
                                   fontWeight = FontWeight.Light
                               )
                           }
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
                        ListCardMessage()
                    }
                }
            }
        )
    }
}



@Composable
fun ProjectCompleteCard(notification: Notification) {
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
                imageVector = Icons.Default.Check,
                contentDescription = "Project Complete Icon",
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "${notification.message}",
                style = MaterialTheme.typography.h6
            )
        }
    }
}


@Composable
fun ListCardMessage(){
    val viewModel = viewModel(NotificationViewModel::class.java)
    val state by viewModel.state.collectAsState()
    LazyColumn {
        if (state.notificationList.isNotEmpty()){
        items(state.notificationList) { notification ->
          ProjectCompleteCard(notification)
        }
    }else {
            item {
                EmptyContent()
            }
        }
    }
}

