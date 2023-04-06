package com.example.myapplication.view.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class Navigation: ViewModel()  {

    @Composable
    fun barNavigation(){
        TopAppBar(
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription ="Menu",
                    tint = Color.White
                )
            },
            title = {
                Text(
                    text = "TeamFlow",
                    color= Color.White,
                    modifier = Modifier.padding(horizontal = 50.dp)
                )
            },
            backgroundColor= Color(color = 0xFF1E88E5)
        )
    }
}