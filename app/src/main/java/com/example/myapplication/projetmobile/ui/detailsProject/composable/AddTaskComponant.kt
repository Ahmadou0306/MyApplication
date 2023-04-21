package com.example.myapplication.projetmobile.ui.detailsProject.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.projetmobile.dataSource.models.Member
import com.example.myapplication.projetmobile.dataSource.models.Task
import com.example.myapplication.projetmobile.ui.detailsProject.colorPersonnel
import com.example.myapplication.projetmobile.viewsmodels.MemberViewModel
import com.example.myapplication.projetmobile.viewsmodels.TaskViewModel

@Composable
fun AddTaskModal(showDialog: MutableState<Boolean>, selectedId:Int){

    val viewModel = viewModel(TaskViewModel::class.java)
    // Les états du formulaire
    val nameState = remember { mutableStateOf(TextFieldValue()) }
    val descriptionState = remember { mutableStateOf(TextFieldValue()) }
    val dueDateState = remember { mutableStateOf(TextFieldValue()) }
    val finDateState = remember { mutableStateOf(TextFieldValue()) }
    val assignedMembersState = remember { mutableStateOf(TextFieldValue()) }


    fun initValue(){
        // Réinitialiser les champs
        nameState.value = TextFieldValue("")
        descriptionState.value = TextFieldValue("")
        dueDateState.value = TextFieldValue("")
        finDateState.value=TextFieldValue("")
        assignedMembersState.value = TextFieldValue("")
    }
    fun addTask(){
        val task= Task(
          0,
          nameState.value.text,
          descriptionState.value.text,
          dueDateState.value.text,
            finDateState.value.text,
            false,
            assignedMembersState.value.text
        )
        initValue()
        viewModel.add(task)
    }

// Modal
    if (showDialog.value) {
        Dialog(
            onDismissRequest = { showDialog.value = false },
            content = {
                //formulaire
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 30.dp)
                        .background(Color.White)
                        .padding(horizontal = 40.dp)
                    ,

                ) {
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
                        label = { Text("Date debut") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = finDateState.value,
                        onValueChange = { finDateState.value = it },
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
        )
    }
}