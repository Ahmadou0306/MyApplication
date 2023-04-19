package com.example.myapplication.projetmobile.ui.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.myapplication.projetmobile.dataSource.models.Task
import com.example.myapplication.projetmobile.dataSource.models.TaskModel
import com.example.myapplication.projetmobile.viewsmodels.TaskViewModel


@Composable
fun AddTaskScreen(navController: () -> Unit) {
    val viewModel = viewModel(TaskViewModel::class.java)
    val state by viewModel.state.collectAsState()
    // Les états du formulaire
    val nameState = remember { mutableStateOf(TextFieldValue()) }
    val descriptionState = remember { mutableStateOf(TextFieldValue()) }
    val dueDateState = remember { mutableStateOf(TextFieldValue()) }
    val assignedMembersState = remember { mutableStateOf(TextFieldValue()) }
    fun reiniText(){
        // Réinitialiser les champs
        nameState.value = TextFieldValue("")
        descriptionState.value =TextFieldValue("")
        dueDateState.value =TextFieldValue("")
        assignedMembersState.value =TextFieldValue("")
    }

    // La fonction pour valider le formulaire et ajouter une tâche
    fun addTask(){
        val taskModels= TaskModel(
            0,
            "d",
            "hd",
            "hd",
            false,
            "junior"
        )
        val task= Task(
            taskModels.id,
            taskModels.name,
            taskModels.description,
            taskModels.dueDate,
            taskModels.isCompleted,
            taskModels.assignedMembers
        )
        reiniText()
    }
    // Le formulaire
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Ajouter une tâche", style = MaterialTheme.typography.h5)

        OutlinedTextField(
            value = nameState.value,
            onValueChange = { nameState.value = it },
            label = { Text("Nom de la tâche") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = descriptionState.value,
            onValueChange = { descriptionState.value = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = dueDateState.value,
            onValueChange = { dueDateState.value = it },
            label = { Text("Date d'échéance") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = assignedMembersState.value,
            onValueChange = { assignedMembersState.value = it },
            label = { Text("Membres assignés") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                      addTask()
                      },
            modifier = Modifier
                .padding(vertical = 16.dp)
        ) {
            Text("Ajouter la tâche")
        }

    }
}

