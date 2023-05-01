package com.example.myapplication.projetmobile.ui.detailsProject.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.projetmobile.dataSource.models.Member
import com.example.myapplication.projetmobile.ui.componant.addError
import com.example.myapplication.projetmobile.viewsmodels.MemberViewModel

@Composable
fun AddMemberModal(  showDialog:MutableState<Boolean>,selectedId:Int){

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
                                text = "Formulaire d'ajout d'un membre",
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
                        addMemberDiaolog(showDialog, selectedId)
                    }
                }
            }
        )
    }
}

@Composable
fun addMemberDiaolog(showDialog:MutableState<Boolean>,selectedId:Int){
    val viewModel = viewModel(MemberViewModel::class.java)
    val showDialog = remember { mutableStateOf(false) }
    val id = remember { mutableStateOf(0) }
    val idProject = remember { mutableStateOf(selectedId) }
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

    fun initValue(){
        id.value=0
        name.value=""
        email.value=""

    }
    fun addMember(){
        if(name.value.isNotBlank() && email.value.isNotBlank()){
            val member= Member(
                id.value,
                idProject.value,
                name.value,
                email.value
            )
            viewModel.addMember(member)
            initValue()
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
    ) {

//        Spacer(modifier = Modifier.padding(vertical=8.dp))
//        OutlinedTextField(
//            value = id.value.toString(),
//            modifier = Modifier.fillMaxWidth(),
//            shape= CutCornerShape(topStart = 15.dp, bottomEnd = 15.dp),
//            onValueChange = { newValue ->
//                id.value = newValue.toIntOrNull()?:0
//            },
//            label = { Text(text = " id", color = Color(color = 0xFF1E88E5)) },
//            textStyle = TextStyle(
//                fontSize = 16.sp
//            ),
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                textColor = MaterialTheme.colors.onSurface,
//                focusedBorderColor = Color(color = 0xFF1E88E5),
//                cursorColor = MaterialTheme.colors.onSurface,
//            ),
//            enabled = false
//        )
//
//        Spacer(modifier = Modifier.padding(vertical=8.dp))
//        OutlinedTextField(
//            value = idProject.value.toString(),
//            modifier = Modifier.fillMaxWidth(),
//            shape= CutCornerShape(topStart = 15.dp, bottomEnd = 15.dp),
//            onValueChange = { newValue ->
//                idProject.value = newValue.toIntOrNull()?:0
//            },
//            label = { Text(text = " id Project", color = Color(color = 0xFF1E88E5)) },
//            textStyle = TextStyle(
//                fontSize = 16.sp
//            ),
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                textColor = MaterialTheme.colors.onSurface,
//                focusedBorderColor = Color(color = 0xFF1E88E5),
//                cursorColor = MaterialTheme.colors.onSurface,
//            ),
//            enabled = false
//        )
//
//        Spacer(modifier = Modifier.padding(vertical=8.dp))
        OutlinedTextField(
            value = name.value,
            modifier = Modifier.fillMaxWidth(),
            shape= CutCornerShape(topStart = 15.dp, bottomEnd = 15.dp),
            onValueChange = { newValue ->
                name.value = newValue
            },
            label = { Text(text = " Name Member", color = Color(color = 0xFF1E88E5)) },
            textStyle = TextStyle(
                fontSize = 16.sp
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onSurface,
                focusedBorderColor = Color(color = 0xFF1E88E5),
                cursorColor = MaterialTheme.colors.onSurface,
            )
        )


        Spacer(modifier = Modifier.padding(vertical=8.dp))
        OutlinedTextField(
            value = email.value,
            modifier = Modifier.fillMaxWidth(),
            shape= CutCornerShape(topStart = 15.dp, bottomEnd = 15.dp),
            onValueChange = { newValue ->
                email.value = newValue
            },
            label = { Text(text = "E-mail", color = Color(color = 0xFF1E88E5)) },
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
        Button(
            onClick = { addMember() },
            colors = ButtonDefaults.buttonColors( backgroundColor = Color(color = 0xFF1E88E5)) ,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            shape= CutCornerShape(topStart = 7.dp, bottomEnd = 7.dp),
            contentPadding = PaddingValues(10.dp)
        )
        {
            Text(text = "Ajouter", color = Color(color = 0xFFFFFFFF))

        }
        Spacer(modifier = Modifier.padding(8.dp))
    }
    addError(showDialog)
}