
package com.example.myapplication.projetmobile.ui.detailsProject

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.projetmobile.dataSource.models.Task
import com.example.myapplication.projetmobile.ui.detailsProject.composable.AddMemberModal
import com.example.myapplication.projetmobile.ui.detailsProject.composable.AddSubProjectModal
import com.example.myapplication.projetmobile.ui.detailsProject.composable.AddTaskModal
import com.example.myapplication.projetmobile.ui.detailsProject.composable.MembersList
import com.example.myapplication.projetmobile.viewsmodels.MemberViewModel
import com.example.myapplication.projetmobile.viewsmodels.TaskViewModel

const val colorPersonnel=0xFF1E88E5

data class ActionButtonData(val icon: ImageVector, val text: String, val action: () -> Unit)

@Composable
fun ActionButton(buttonData: ActionButtonData, onNavigate: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.clickable {
            buttonData.action()
            onNavigate()
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
fun ActionIcons(selectedId: Int, onNavigate: () -> Unit) {


    var showDialogMember = remember { mutableStateOf(false) }
    var showDialogSubProject = remember { mutableStateOf(false) }
    var showDialogTask = remember { mutableStateOf(false) }
    val buttonData = listOf(
        ActionButtonData(Icons.Default.Create, "Add Sous-projet") {showDialogSubProject.value=true},
        ActionButtonData(Icons.Default.Add, "Add tâche") {showDialogTask.value=true},
        ActionButtonData(Icons.Default.Person, "Ajouter Un Membre") { showDialogMember.value = true },
        ActionButtonData(Icons.Default.Info, "Diagram") {}
    )

    LazyRow(
        contentPadding = PaddingValues(start = 12.dp, top = 10.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(25.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(buttonData) { data ->
            ActionButton(data,onNavigate={
                when(data){
                    buttonData[3]->onNavigate
                    else->{}
                }
            })
        }
    }
    AddMemberModal(showDialogMember,selectedId)
    AddSubProjectModal(showDialogSubProject, selectedId)
    AddTaskModal(showDialogTask , selectedId)
}

@Composable
fun ListMembers(){
    val viewModel = viewModel(MemberViewModel::class.java)
    MembersList(members = viewModel.memberList)
}

@Composable
fun DetailHome(
    selectedId: Int,
    onNavigate: () -> Unit
) {
    Surface {
        Column {
            Box{
                Header()
            }
            Box {
                ActionIcons(selectedId,onNavigate)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 0.dp, horizontal = 16.dp)
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(colorPersonnel))
                ) {
                    Text(text = "Supprimer Le Projet", color = Color.White)
                }
            }
            Box {
                TroisCards()
            }

            Box {
               ListMembers()
            }

        }
    }


}@Composable
fun TroisCards() {
    val cardPadding = 16.dp
    val cards = listOf(
        R.drawable.dice_3,
        R.drawable.dice_3,
        R.drawable.dice_4
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(cards) { index, card ->
            Box(
                Modifier.padding(
                    start = cardPadding,
                    end = cardPadding,
                    top = 50.dp,
                )
            ) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 4.dp,
                    modifier = Modifier
                        .width(300.dp)
                        .height(250.dp)
                        .padding(bottom = 16.dp)
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
                            onClick = { /* Action à effectuer */ },
                            colors = ButtonDefaults.buttonColors(Color(colorPersonnel))
                        ) {
                            Text(when (index) {
                                0 -> "Membres"
                                1 -> "Tâches"
                                2 -> "Sous-project"
                                else -> ""
                            })
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun Header(){
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
                text = "Titre du projet",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // Créer un Text pour le nom du chef de projet
            Text(
                text = "Chef de project:  Nom",
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

