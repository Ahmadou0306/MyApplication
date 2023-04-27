
package com.example.myapplication.projetmobile.ui.project

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.projetmobile.dataSource.models.Project
import com.example.myapplication.projetmobile.ui.home.componant.BottomBar
import com.example.myapplication.projetmobile.ui.home.componant.FloatingActionButtonComp
import com.example.myapplication.projetmobile.ui.home.componant.drawerView
import com.example.myapplication.projetmobile.viewsmodels.ProjectViewModel
import kotlinx.coroutines.delay
import java.util.*
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddFormProject(
    onFormNavigate: () -> Unit,
    onNavigate: (Project?) -> Unit,
    onHomeNavigate:()->Unit,
){
    Scaffold(
        content = {
            ScaffoldContain(onFormNavigate)
        },
        // pass the drawer
        drawerContent = {
            drawerView()
        },
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            // Create a floating action button in
            // floatingActionButton parameter of
            FloatingActionButtonComp(onNavigate =onNavigate)
        },

        // pass the bottomBar
        // we created
        bottomBar = {
            BottomBar(onHomeNavigate)
        }

    )

}
@Composable
fun ScaffoldContain(onFormNavigate: () -> Unit){
    val colorValidation = Color(0xFFB8FCFF)
    var formClickedScaffold by remember { mutableStateOf(false) }
    val viewModel = viewModel(ProjectViewModel::class.java)
    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int
    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()
    val description = remember { mutableStateOf(TextFieldValue()) }
    val name = remember { mutableStateOf(TextFieldValue()) }
    val chefName = remember { mutableStateOf(TextFieldValue()) }
    val dateStart = remember { mutableStateOf("") }
    val dateEnd = remember { mutableStateOf("") }

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            dateStart.value = "$year-$month-$dayOfMonth"
        }, year, month, day
    )
    val datePickerDialog2 = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            dateEnd.value = "$year-$month-$dayOfMonth"
        }, year, month, day
    )

    fun initialForm() {
        description.value = TextFieldValue("")
        name.value = TextFieldValue("")
        chefName.value = TextFieldValue("")
        description.value = TextFieldValue("")
        dateEnd.value = ""
        dateStart.value = ""
    }

    fun addProject() {
        val project = Project(
            0,
            name.value.text,
            chefName.value.text,
            description.value.text,
            dateStart.value,
            dateEnd.value
        )
        viewModel.addProject(project = project)
    }

    @Composable
    fun afficheMessageValidation() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Alert",
                color= Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = "Le projet à été créé avec succés !",
                color= Color.Blue,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        LaunchedEffect(formClickedScaffold) {
            delay(3000)
            formClickedScaffold=false
        }
    }
    @Composable
    fun afficheMessageValidation2() {
        Dialog(
                onDismissRequest = { /*TODO*/ },
            ) {
                Text("Le projet à été créé avec succés !",
                    color = colorValidation,
                    textAlign = TextAlign.Center)
                LaunchedEffect(formClickedScaffold) {
                    delay(3000)
                    formClickedScaffold=false
                }
            }

    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = name.value,
            modifier = Modifier.fillMaxWidth(),
            shape = CutCornerShape(topStart = 15.dp, bottomEnd = 15.dp),
            onValueChange = {
                name.value = it
            },
            label = { Text(text = "Project Name", color = Color(color = 0xFF1E88E5)) },
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
            value = chefName.value,
            modifier = Modifier.fillMaxWidth(),
            shape = CutCornerShape(topStart = 15.dp, bottomEnd = 15.dp),
            onValueChange = {
                chefName.value = it
            },
            label = { Text(text = "Project Manager", color = Color(color = 0xFF1E88E5)) },
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
            value = description.value,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                description.value = it
            },
            shape = CutCornerShape(topStart = 15.dp, bottomEnd = 15.dp),
            label = { Text(text = "Project Description", color = Color(color = 0xFF1E88E5)) },
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
            value = dateStart.value,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                dateStart.value = it
            },
            label = { Text(text = "Project Start", color = Color(color = 0xFF1E88E5)) },
            readOnly = true,
            trailingIcon = {
                FloatingActionButton(
                    onClick = { datePickerDialog.show() },
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
            shape = CutCornerShape(topStart = 15.dp, bottomEnd = 15.dp),
        )
        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedTextField(
            value = dateEnd.value,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { dateEnd.value = it },
            label = { Text(text = "Project Deadline", color = Color(color = 0xFF1E88E5)) },
            readOnly = true,
            trailingIcon = {
                FloatingActionButton(
                    onClick = { datePickerDialog2.show() },
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
            shape = CutCornerShape(topStart = 15.dp, bottomEnd = 15.dp),
        )

        Spacer(modifier = Modifier.padding(8.dp))


        Button(
            onClick = {
                formClickedScaffold = true
                addProject()
                initialForm()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(color = 0xFF1E88E5)),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            shape = CutCornerShape(topStart = 7.dp, bottomEnd = 7.dp),
            contentPadding = PaddingValues(16.dp)
        )
        {
            Text(text = "Create Project", color = Color(color = 0xFFFFFFFF))
        }
        Spacer(modifier = Modifier.padding(16.dp))

        //fonction alert
        if(formClickedScaffold){
            afficheMessageValidation2()
        }
    }



}

