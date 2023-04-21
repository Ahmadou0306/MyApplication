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
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.projetmobile.dataSource.models.Member
import com.example.myapplication.projetmobile.ui.detailsProject.colorPersonnel
import com.example.myapplication.projetmobile.viewsmodels.MemberViewModel

@Composable
fun AddMemberModal(  showDialog:MutableState<Boolean>,selectedId:Int){

    val viewModel = viewModel(MemberViewModel::class.java)

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
        val member= Member(
            id.value,
            idProject.value,
            name.value,
            email.value
        )
        viewModel.addMember(member)
    }

// Modal
    if (showDialog.value) {
        Dialog(
            onDismissRequest = { showDialog.value = false },
            content = {
                //Formulaire
                Column(
                    modifier = Modifier
                        .padding(25.dp)
                        .background(Color(colorPersonnel))
                ) {
                    Text("Formulaire",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp,),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        letterSpacing = 0.2.em,
                        style = TextStyle(
                            letterSpacing = 0.2.em
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                        ,
                        value = id.value.toString(),
                        onValueChange = { id.value = it.toIntOrNull() ?: 0 },
                        label = { Text("ID") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                        ,
                        value = idProject.value.toString(),
                        onValueChange = { idProject.value = it.toIntOrNull() ?: 0 },
                        label = { Text("Id Project") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                        ,
                        value = name.value,
                        onValueChange = { name.value = it },
                        label = { Text("Nom") }
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                        ,
                        value = email.value,
                        onValueChange = { email.value = it },
                        label = { Text("Email") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        )
                    )
                    // Boutons "Annuler" et "Valider"
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = { showDialog.value = false },
                            colors = ButtonDefaults.buttonColors(Color.White)
                        ) {
                            Text("Annuler")
                        }
                        TextButton(
                            onClick = {
                                addMember()
                                initValue()
                            },
                            colors = ButtonDefaults.buttonColors(Color.White)
                        ) {
                            Text("Valider")
                        }
                    }
                }
            }
        )
    }
}