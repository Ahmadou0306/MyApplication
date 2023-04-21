package com.example.myapplication.projetmobile.ui.home.componant

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel


     @Composable
     fun barNavigation(onMenuClicked:()-> Unit) {
         TopAppBar(
             navigationIcon = {
                 Icon(
                     imageVector = Icons.Default.Menu,
                     contentDescription = "Menu",
                     tint = Color.White,
                     modifier = Modifier.clickable(onClick=onMenuClicked)


                 )
             },
             title = {
                 Text(
                     text = "TeamFlow",
                     color = Color.White,
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(start = 70.dp)
                     ,

                 )
             },
             backgroundColor = Color(color = 0xFF1E88E5)
         )

     }

