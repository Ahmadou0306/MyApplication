package com.example.myapplication.view.layout

import android.annotation.SuppressLint
import android.graphics.Paint.Style
import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.myapplication.R

class Drawer:ViewModel() {
    private val colorPersonnel =0xFF1E88E5

    @SuppressLint("SuspiciousIndentation")
    @Composable
    fun drawerView() {
        Column(
                Modifier
                    .background(Color.White)
                    .fillMaxSize(),
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
    }




