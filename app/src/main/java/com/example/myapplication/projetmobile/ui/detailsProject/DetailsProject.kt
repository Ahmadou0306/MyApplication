
package com.example.myapplication.projetmobile.ui.detailsProject

import android.annotation.SuppressLint
import android.transition.CircularPropagation
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.projetmobile.dataSource.models.Project
import com.example.myapplication.projetmobile.dataSource.models.Task
import com.example.myapplication.projetmobile.ui.detailsProject.composable.AddMemberModal
import com.example.myapplication.projetmobile.ui.detailsProject.composable.AddSubProjectModal
import com.example.myapplication.projetmobile.ui.detailsProject.composable.AddTaskModal
import com.example.myapplication.projetmobile.ui.detailsProject.composable.DiagramModal
import com.example.myapplication.projetmobile.ui.detailsProject.composable.ListMemberModal
import com.example.myapplication.projetmobile.ui.detailsProject.composable.SubProjectModal
import com.example.myapplication.projetmobile.ui.detailsProject.composable.TaskModal
import com.example.myapplication.projetmobile.ui.home.componant.BottomBar
import com.example.myapplication.projetmobile.ui.home.componant.FloatingActionButtonComp
import com.example.myapplication.projetmobile.viewsmodels.ProjectViewModel
import com.example.myapplication.projetmobile.viewsmodels.TaskViewModel
import kotlinx.coroutines.launch

const val colorPersonnel=0xFF1E88E5

data class ActionButtonData(val icon: ImageVector, val text: String, val action: () -> Unit)

@Composable
fun ActionButton(buttonData: ActionButtonData) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.clickable {
            buttonData.action()
        }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            IconButton(
                onClick = buttonData.action,
                modifier = Modifier
                    .width(35.dp)
                    .border(
                        2.dp,
                        Color.Green,
                        CircleShape
                    )
            ) {
                Icon(
                    imageVector = buttonData.icon,
                    contentDescription = "",
                    modifier = Modifier
                        .size(45.dp)
                        .padding(5.dp)
                )
            }
            Text(
                text = buttonData.text,
                fontSize = 15.sp,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
            )
        }
    }
}
@Composable
fun ActionIcons(selectedId: Int) {


    var showDialogMember = remember { mutableStateOf(false) }
    var showDialogSubProject = remember { mutableStateOf(false) }
    var showDialogTask = remember { mutableStateOf(false) }
    var showDiagram = remember { mutableStateOf(false) }
    val buttonData = listOf(
        ActionButtonData(Icons.Default.Create, "Ajouter Sous-projet") {showDialogSubProject.value=true},
        ActionButtonData(Icons.Default.Add, "Ajouter tâche") {showDialogTask.value=true},
        ActionButtonData(Icons.Default.Person, "Ajouter Un Membre") { showDialogMember.value = true },
        ActionButtonData(Icons.Default.Info, "Diagramme") { showDiagram.value=true }
    )

    LazyRow(
        contentPadding = PaddingValues(start = 12.dp, top = 10.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(25.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(buttonData) { data ->
            ActionButton(data)
        }
    }
    AddMemberModal(showDialogMember,selectedId)
    AddSubProjectModal(showDialogSubProject, selectedId)
    AddTaskModal(showDialogTask , selectedId)
    DiagramModal(showDiagram, selectedId)

}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailHome(
    selectedId: Int,
    onNavigateFloat: (Project?) -> Unit,
    onHomeNavigate:()->Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    val viewModelProject = viewModel(ProjectViewModel::class.java)
    var projectExtract by remember {
        mutableStateOf<Project?>(null)
    }
    LaunchedEffect(key1 = selectedId) {
        viewModelProject.getProjectById(selectedId).collect { project ->
            projectExtract=project
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Confirmation") },
            text = { Text("Êtes-vous sûr de vouloir supprimer ce projet?") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModelProject.deleteProject(projectExtract)
                        onHomeNavigate()
                        CircularPropagation()
                        showDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(colorPersonnel))
                ) {
                    Text("Oui", color = Color.White)
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(colorPersonnel)),

                ) {
                    Text("Non", color = Color.White)
                }
            }
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 1.dp,
                modifier = Modifier.height(130.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .clip(
                            CutCornerShape(
                                topStart = 30.dp,
                                topEnd = 0.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 30.dp,
                            )
                        )
                        .background(Color(colorPersonnel))
                ) {
                    Header(selectedId)
                }
            }
        },
        content = {
            Surface {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                    ,

                        ) {
                    Box {
                        ActionIcons(selectedId)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 0.dp, horizontal = 16.dp)
                    ) {
                        Button(
                            onClick = { showDialog = true },
                            modifier = Modifier.align(Alignment.Center),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(colorPersonnel))
                        ) {
                            Text(text = "Supprimer Le Projet", color = Color.White)
                        }
                    }
                    Box {
                        TroisCards(selectedId)
                    }
                }
            }
        },
        drawerContent = {
            // TODO: Add drawer content
        },
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButtonComp(onNavigate = onNavigateFloat)
        },
        bottomBar = {
            BottomBar(onHomeNavigate)
        }
    )
}

@Composable
fun TroisCards(idProject:Int) {
    var showListDialogMember = remember { mutableStateOf(false) }
    var showListDialogTask = remember { mutableStateOf(false) }
    var showListDialogSubProject = remember { mutableStateOf(false) }

    val cardPadding = 16.dp
    val cards = listOf(
        R.drawable.member,
        R.drawable.tachejpg,
        R.drawable.subproject
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        itemsIndexed(cards) { index, card ->
            Box(
                Modifier.padding(
                    start = cardPadding,
                    end = cardPadding,
                    top = 35.dp,
                )
            ) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 4.dp,
                    modifier = Modifier
                        .width(300.dp)
                        .height(290.dp)
                        .padding(bottom = 16.dp, top = 5.dp)
                ) {
                    Column(
                        Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(card),
                            contentDescription = "Image de fond",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(170.dp)
                                .scale(0.5f)
                        )
                        Button(
                            onClick = {
                                      /* Action à effectuer */
                                      when(index){
                                          0->showListDialogMember.value=true
                                          1->showListDialogTask.value=true
                                          2->showListDialogSubProject.value=true
                                          else->{}
                                      }
                                      },
                            colors = ButtonDefaults.buttonColors(Color(colorPersonnel))
                        ) {
                            Text(when (index) {
                                0 -> "Membres"
                                1 -> "Tâches"
                                2 -> "Sous-projet"
                                else -> ""
                            }, color = Color.White)

                            ListMemberModal(showListDialogMember,idProject)
                            TaskModal(showListDialogTask,idProject)
                            SubProjectModal(showListDialogSubProject,idProject)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Header(id:Int){
    val viewModelMember = viewModel(ProjectViewModel::class.java)
    var projectStateName = remember { mutableStateOf<Project?>(null) }

    LaunchedEffect(key1 = id) {
        viewModelMember.getProjectById(id).collect { project ->
            projectStateName.value = project
        }
    }


    Surface(
        color = Color(colorPersonnel),
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clip(
                CutCornerShape(
                    topStart = 30.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 30.dp,
                )
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Créer un Text pour le titre du projet
            Text(
                text = "${projectStateName.value?.name}",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // Créer un Text pour le nom du chef de projet
            Text(
                text = "Chef de project:  ${projectStateName.value?.chefName}",
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





@Composable
fun TaskCard3(task: Task) {
    val viewModel = viewModel(TaskViewModel::class.java)

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
                    onClick = {
                              viewModel.delete(task)
                    },
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

