package com.example.myapplication.view.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch

 class Navigation: ViewModel()  {


    @Composable
    fun barNavigation(/*onMenuCliked: () -> Unit*/){

        var enableDrawer by remember { mutableStateOf(false) }
        if(enableDrawer){
              //  com.example.myapplication.view.layout.Drawer().drawerView()

        }else{
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = Color.White,
                   //     modifier = Modifier.clickable(onClick =  onMenuCliked )


                    )
                },
                title = {
                    Text(
                        text = "TeamFlow",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 50.dp)
                    )
                },
                backgroundColor = Color(color = 0xFF1E88E5)
            )
        }
    }


}
