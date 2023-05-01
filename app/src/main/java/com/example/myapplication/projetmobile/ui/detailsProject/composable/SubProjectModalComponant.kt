package com.example.myapplication.projetmobile.ui.detailsProject.composable

import android.transition.CircularPropagation
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.projetmobile.dataSource.models.SousProject
import com.example.myapplication.projetmobile.ui.componant.EmptyContentList
import com.example.myapplication.projetmobile.ui.detailsProject.colorPersonnel
import com.example.myapplication.projetmobile.viewsmodels.SubProjectViewModel

@Composable
fun ShowSubProjects(idProject: Int) {
    val viewModel = viewModel(SubProjectViewModel::class.java)
    val state by viewModel.state.collectAsState()
    LazyColumn(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        if(state.memberSelected){
            item {
                CircularProgressIndicator()
            }
        }else{
            if(state.subProjectList.isNotEmpty()){
                items(state.subProjectList) { subProject ->
                    if (subProject.idProject==idProject) SubProjectContainer(subProject)
                }
            }else{
                item {
                    EmptyContentList()
                }
            }
        }
    }
}
@Composable
fun SubProjectContainer(subProject: SousProject) {
    var showDialogConfirmation by remember { mutableStateOf(false) }
    val viewModel = viewModel(SubProjectViewModel::class.java)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = subProject.name,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = subProject.description,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Divider(color = MaterialTheme.colors.onBackground.copy(alpha = 0.2f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Date de début",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = subProject.dueDate,
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Date de fin",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = subProject.dueDateFin,
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                IconButton(
                    onClick = {
                              /* Delete action */
                              showDialogConfirmation=true
                              },
                    content = { Icon(Icons.Filled.Delete, contentDescription = "DELETE") }
                )
            }
            // icon ajout tache
        }
    }
    if (showDialogConfirmation) {
        AlertDialog(
            onDismissRequest = { showDialogConfirmation = false },
            title = { Text("Confirmation") },
            text = { Text("Êtes-vous sûr de vouloir supprimer ce Sous Project?") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteSubProject(subProject)
                        CircularPropagation()
                        showDialogConfirmation=false
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(colorPersonnel))
                ) {
                    Text("Oui", color = Color.White)
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialogConfirmation = false },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(colorPersonnel)),

                    ) {
                    Text("Non", color = Color.White)
                }
            }
        )
    }

}

@Composable
fun SubProjectModal(showDialog: MutableState<Boolean>,idProject:Int){
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
                                text = "Liste des Sous-Projets",
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
                        ShowSubProjects(idProject)
                    }
                }
            }
        )
    }
}
