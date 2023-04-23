package com.example.myapplication.projetmobile.ui.detailsProject.composable
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.projetmobile.dataSource.models.Task
import com.example.myapplication.projetmobile.ui.detailsProject.colorPersonnel
import com.example.myapplication.projetmobile.viewsmodels.MemberViewModel
import com.example.myapplication.projetmobile.viewsmodels.TaskViewModel
import androidx.compose.foundation.Image
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.projetmobile.dataSource.models.Member
import com.example.myapplication.projetmobile.dataSource.models.TaskToMember
import com.example.myapplication.projetmobile.viewsmodels.TaskToMemberViewModel


@Composable
fun ListTasks() {
    val viewModel = viewModel(TaskViewModel::class.java)
    val state by viewModel.state.collectAsState()
    LazyColumn(
        modifier = Modifier.background(color = Color.LightGray),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(state.taskList) { task ->
            taskContain(task)
        }
    }
}


@Composable
fun ListMember(id: Int) {
    val viewModel = viewModel(MemberViewModel::class.java)
    val state by viewModel.state.collectAsState()
    LazyColumn(
        modifier = Modifier.background(color = Color.LightGray),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(state.memberList) { member ->
            MemberCard(member,id)
        }
    }
}




@Composable
fun MemberCard(member: Member, id: Int) {
    val viewModel = viewModel(TaskToMemberViewModel::class.java)
    var taskToMember=TaskToMember(
        0,
        id,
        member.id
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp)),
        elevation = 8.dp
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.member),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(28.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = member.name, style = MaterialTheme.typography.h6)
                    Text(text = member.email, style = MaterialTheme.typography.body1)
                }
            }
            Button(
                onClick = {
                          /* Ajouter aux tâches */
                          viewModel.add(taskToMember)
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
            ) {
                Text(text = "Ajouter aux tâches", style = MaterialTheme.typography.button)
            }
        }
    }
}

@Composable
fun taskContain(task : Task) {
    var showDialog = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = Color.White),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.clickable { /* TODO: handle click */ }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Checkbox(
                    checked = true,
                    onCheckedChange = {
                                      /* TODO: handle checked state */

                                      },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Green,
                        uncheckedColor = Color(colorPersonnel)
                    ),
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(
                    text = task.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { /* TODO: handle delete action */ },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete task",
                        tint = MaterialTheme.colors.secondaryVariant,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Text(
                text = task.description,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Start date",
                    tint = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = task.dueDate,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "End date",
                    tint = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = task.finDate,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable {
                        showDialog.value = true
                    },
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Assigned member",
                    tint = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "assigné(s) Membre: ",
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
    addMemberModal(showDialog,task.id)
}
@Composable
fun TaskModal(showDialog: MutableState<Boolean>){
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
                                text = "List Tasks",
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
                        ListTasks()
                    }
                }
            }
        )
    }
}


@Composable
fun addMemberModal(showDialog: MutableState<Boolean>, id: Int){
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
                                text = "Add Member",
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
                        ListMember(id)
                    }
                }
            }
        )
    }
}