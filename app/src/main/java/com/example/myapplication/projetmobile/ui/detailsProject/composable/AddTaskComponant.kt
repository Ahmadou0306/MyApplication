package com.example.myapplication.projetmobile.ui.detailsProject.composable

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.projetmobile.dataSource.models.Task
import com.example.myapplication.projetmobile.ui.componant.addError
import com.example.myapplication.projetmobile.viewsmodels.MemberViewModel
import com.example.myapplication.projetmobile.viewsmodels.TaskViewModel
import java.util.Calendar
import java.util.Date

@Composable
fun AddTaskModal(showDialog: MutableState<Boolean>, selectedId:Int){
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
                                text = "Formulaire de tâche",
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
                        //formulaire
                        AddTaskForm(showDialog, selectedId)
                    }
                }
            }

        )
    }
}

@Composable
fun AddTaskForm(showDialog: MutableState<Boolean>, selectedId:Int){
    //dataBase
    val viewModel = viewModel(TaskViewModel::class.java)
    val viewModelMember = viewModel(MemberViewModel::class.java)
    // Les états du formulaire
    val nameState = remember { mutableStateOf(TextFieldValue()) }
    val descriptionState = remember { mutableStateOf(TextFieldValue()) }
    val dueDateState = remember { mutableStateOf("") }
    val finDateState = remember { mutableStateOf("") }
    var nomSelectedMember by remember { mutableStateOf("") }
    var showDialog = remember { mutableStateOf(false) }


    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int
    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            dueDateState.value = "$dayOfMonth/$month/$year"
        }, year, month, day
    )
    val datePickerDialog2 = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            finDateState.value = "$dayOfMonth/$month/$year"
        }, year, month, day
    )






    fun initValue(){
        // Réinitialiser les champs
        nameState.value = TextFieldValue("")
        descriptionState.value = TextFieldValue("")
        dueDateState.value = ""
        finDateState.value=""
        nomSelectedMember=""
    }
    fun addTask(){
        if(nameState.value.text.isNotBlank()
            && descriptionState.value.text.isNotBlank()
            && dueDateState.value.isNotBlank()
            && finDateState.value.isNotBlank()){
            val task= Task(
                0,
                selectedId,
                nameState.value.text,
                descriptionState.value.text,
                dueDateState.value,
                finDateState.value,
                false,
            )
            initValue()
            viewModel.add(task)
        }else{
            showDialog.value=true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(Color.White)
            .padding(horizontal = 20.dp)
        ,

        ) {

        Spacer(modifier = Modifier.padding(vertical=8.dp))
        OutlinedTextField(
            value = nameState.value,
            modifier = Modifier.fillMaxWidth(),
            shape= CutCornerShape(topStart = 15.dp, bottomEnd = 15.dp),
            onValueChange = { newValue ->
                nameState.value = newValue
            },
            label = { Text(text = "Nom de la tâche", color = Color(color = 0xFF1E88E5)) },
            textStyle = TextStyle(
                fontSize = 16.sp
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onSurface,
                focusedBorderColor = Color(color = 0xFF1E88E5),
                cursorColor = MaterialTheme.colors.onSurface,
            )
        )



        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedTextField(
            value = descriptionState.value,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { newValue ->
                descriptionState.value = newValue
            },
            shape= CutCornerShape(topStart = 15.dp, bottomEnd = 15.dp),
            label = { Text(text = "Description", color = Color(color = 0xFF1E88E5)) },
            maxLines = 5,
            textStyle = TextStyle(
                fontSize = 16.sp
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onSurface,
                focusedBorderColor = Color(color = 0xFF1E88E5),
                cursorColor = MaterialTheme.colors.onSurface,
            )
        )

        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedTextField(
            value = dueDateState.value,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {   newValue ->
                dueDateState.value = newValue
            },
            label = { Text(text = "Date debut tâche", color = Color(color = 0xFF1E88E5)) },
            readOnly = true,
            trailingIcon = {
                FloatingActionButton(onClick = { datePickerDialog.show() },
                    modifier = Modifier.size(35.dp),
                    backgroundColor = Color(color = 0xFF0088FF)
                ) {
                    Icon(Icons.Filled.DateRange, contentDescription = null)
                }
            },
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(
                fontSize = 16.sp
            ),


            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onSurface,
                focusedBorderColor = Color(color = 0xFF1E88E5),
                cursorColor = MaterialTheme.colors.onSurface,
                trailingIconColor = Color(color = 0xFFFFFFFF),
            ),
            shape= CutCornerShape(topStart = 15.dp, bottomEnd = 15.dp),
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = finDateState.value,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {  },
            label = { Text(text = "Date limite tâche", color = Color(color = 0xFF1E88E5)) },
            readOnly = true,
            trailingIcon = {
                FloatingActionButton(onClick = { datePickerDialog2.show() },
                    modifier = Modifier.size(35.dp),
                    backgroundColor = Color(color = 0xFF0088FF)
                ) {
                    Icon(Icons.Filled.DateRange, contentDescription = null)
                }
            },
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(
                fontSize = 16.sp
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onSurface,
                focusedBorderColor = Color(color = 0xFF1E88E5),
                cursorColor = MaterialTheme.colors.onSurface,
                trailingIconColor = Color(color = 0xFFFFFFFF),
            ),
            shape= CutCornerShape(topStart = 15.dp, bottomEnd = 15.dp),
        )

        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = { addTask()}, colors = ButtonDefaults.buttonColors( backgroundColor = Color(color = 0xFF1E88E5)) ,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            shape= CutCornerShape(topStart = 7.dp, bottomEnd = 7.dp),
            contentPadding = PaddingValues(10.dp)
        )
        {
            Text(text = "Ajouter la tâche", color = Color(color = 0xFFFFFFFF))

        }
        Spacer(modifier = Modifier.padding(8.dp))
    }
    addError(showDialog)
}