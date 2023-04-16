package com.example.myapplication.projetmobile.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.myapplication.projetmobile.models.Task
import com.example.myapplication.projetmobile.viewsmodels.TaskViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class TaskForm :ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel:TaskViewModel= viewModel()
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    AddTaskScreen(viewModel = viewModel)
                }
            }
        }
    }
}

data class TaskFormData(var name: String, var description: String, var dueDate: String, var assignedMembers: String)

@SuppressLint("SuspiciousIndentation")
@Composable
fun AddTaskScreen(viewModel: TaskViewModel) {
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

                        viewModel.addTask(Task(0,"d","d","s",false,"df"))
                      },
            modifier = Modifier
                .padding(vertical = 16.dp)
        ) {
            Text("Ajouter la tâche")
        }
        Button(
            onClick = {
                      taskListScreen(viewModel)
                      },
            modifier = Modifier
                .padding(vertical = 16.dp)
        ) {
            Text("Liste")
        }
    }
}

fun taskListScreen(
    viewModel: TaskViewModel
) {
    val tasks = viewModel.allTasks
    
    Log.d("d","${tasks.value}")
    
}
