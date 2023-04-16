@file:Suppress("NAME_SHADOWING")

package com.example.myapplication

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Preview(showBackground = true)
@Composable
fun FormProject(){
    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int
    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()
    val dateStart = remember { mutableStateOf("") }
    val dateEnd = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            dateStart.value = "$dayOfMonth/$month/$year"
        }, year, month, day
    )
    val datePickerDialog2 = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            dateEnd.value = "$dayOfMonth/$month/$year"
        }, year, month, day
    )
    var description by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }


    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        OutlinedTextField(
            value = name,
            modifier = Modifier.fillMaxWidth(),
            shape= CutCornerShape(topStart = 15.dp, bottomEnd = 15.dp),
            onValueChange = { newValue ->
                name = newValue
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
            value = description,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { newValue ->
                description = newValue
            },
            shape= CutCornerShape(topStart = 15.dp, bottomEnd = 15.dp),
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
            onValueChange = {  },
            label = { Text(text = "Project Start", color = Color(color = 0xFF1E88E5)) },
            readOnly = true,
            trailingIcon = {
                FloatingActionButton(onClick = { datePickerDialog.show() },
                    modifier = Modifier.size(35.dp),
                    backgroundColor = Color(color = 0xFF0088FF)
                ) {
                    Icon(Icons.Filled.DateRange, contentDescription = null)
                }
            },
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = KeyboardType.Number
//            ),
//            keyboardActions = KeyboardActions(
//                onDone = {   }
//            ),
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
            value = dateEnd.value,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {  },
            label = { Text(text = "Project Deadline", color = Color(color = 0xFF1E88E5)) },
            readOnly = true,
            trailingIcon = {
                FloatingActionButton(onClick = { datePickerDialog2.show() },
                    modifier = Modifier.size(35.dp),
                    backgroundColor = Color(color = 0xFF0088FF)
                ) {
                    Icon(Icons.Filled.DateRange, contentDescription = null)
                }
            },
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = KeyboardType.Number
//            ),
//            keyboardActions = KeyboardActions(
//                onDone = {   }
//            ),
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

//        OutlinedTextField(
//            value = "",
//            modifier = Modifier.fillMaxWidth(),
//            shape= CutCornerShape(topStart = 15.dp, bottomEnd = 15.dp),
//            onValueChange = {  },
//            label = { Text(text = "Sub-Project", color = Color(color = 0xFF1E88E5)) },
//            maxLines = 5,
//            textStyle = TextStyle(fontSize = 16.sp
//            ),
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = KeyboardType.Text
//            ),
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                textColor = MaterialTheme.colors.onSurface,
//                focusedBorderColor = Color(color = 0xFF1E88E5),
//                cursorColor = MaterialTheme.colors.onSurface,
//            )
//        )
//        Spacer(modifier = Modifier.padding(12.dp))

        Button(onClick = { }, colors = ButtonDefaults.buttonColors( backgroundColor = Color(color = 0xFF1E88E5)) ,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            shape= CutCornerShape(topStart = 7.dp, bottomEnd = 7.dp),
            contentPadding = PaddingValues(16.dp)
        )
        {
            Text(text = "Create Project", color = Color(color = 0xFFFFFFFF))

        }

    }
}