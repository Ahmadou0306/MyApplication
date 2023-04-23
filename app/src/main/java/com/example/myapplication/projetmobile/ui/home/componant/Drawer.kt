package com.example.myapplication.projetmobile.ui.home.componant

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel


    private const val colorPersonnel =0xFF1E88E5

     @Composable
     fun drawerView() {
         Column(
             modifier = Modifier.fillMaxWidth().background(Color.White),
             verticalArrangement= Arrangement.Top,
         ) {

             drawerViewUser()
             //tache avec les icones
             drawerViewTask()
         }

     }


    @Composable
    fun drawerViewUser() {
        Row(

        ) {
            Box(
                modifier = Modifier.width(10.dp).height(10.dp)
                    .clip(shape= CircleShape)
                 //   .border(width= 5.dp, shape= CircleShape, Color.Black)
                    .padding(5.dp),
                    contentAlignment= Alignment.CenterStart
            ){
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Menu",
                    tint = Color.White,
                )
            }
            Box(
                modifier = Modifier.width(10.dp).height(10.dp)
                    .clip(shape= CircleShape)
                   // .border(width= 5.dp, shape= CircleShape, Color.Black)
                    .padding(5.dp),
                contentAlignment= Alignment.CenterEnd
            ){
                Column() {
                    Text("Utilisateur: NDiaye Ahmadou")
                    Spacer(Modifier.height(8.dp))
                    Text("Manager ")
                    Spacer(Modifier.height(8.dp))
                    Text("TeamFlow Compagnie ")

                }
            }
        }

    }

     @SuppressLint("SuspiciousIndentation")
    @Composable
    private fun drawerViewTask() {
        Column(
                Modifier.fillMaxSize(),
            Arrangement.Center


        ) {

            //Premier ligne Accueil
            IconButton(
                modifier = Modifier.fillMaxWidth().padding(bottom = 25.dp),
                onClick = {
                    println("Clicked")
                }
            ) {
                Row() {
                    //icone
                    Icon(Icons.Filled.Home, "contentDescription", tint = Color.Blue)
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "Accueil", color = Color.Blue,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

                //Seconde ligne Accueil
                IconButton(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 25.dp),
                    onClick = {
                        println("Clicked")
                    }
                ) {
                    Row() {
                        //icone
                        Icon(Icons.Filled.Search, "contentDescription", tint = Color.Blue)
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "Recherche", color = Color.Blue,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )
                    }
                }
            Spacer(modifier = Modifier.height(8.dp))
            //Troisieme ligne Accueil
                    IconButton(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 25.dp),
                        onClick = {
                            println("Clicked")
                        }
                    ) {
                        Row() {
                            //icone
                            Icon(Icons.Filled.Info, "contentDescription", tint = Color.Blue)
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "Info", color = Color.Blue,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            )
                        }
                    }
            }
        }





