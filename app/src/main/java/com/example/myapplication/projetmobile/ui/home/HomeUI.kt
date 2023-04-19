package com.example.myapplication.projetmobile.ui.home

import androidx.compose.animation.core.*

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.projetmobile.NavRoute
import com.example.myapplication.projetmobile.ui.home.Body
import com.example.myapplication.projetmobile.ui.home.BottomBar
import com.example.myapplication.projetmobile.ui.home.Drawer
import com.example.myapplication.projetmobile.ui.home.TopBarMenu
import kotlinx.coroutines.launch



@Composable
fun Home(navHostController: NavHostController) {

    // create a scaffold state, set it to close by default
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    // Create a coroutine scope. Opening of
    // background thread without blocking main thread
    val  carotineScop = rememberCoroutineScope()
    // Scaffold Composable
    Scaffold(

        // pass the scaffold state
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarMenu (
                onMenuClicked = {
                    carotineScop.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },

        // pass the bottomBar
        // we created
        bottomBar = { BottomBar() },

        // Pass the body in
        // content parameter
        content = { Body() },

        // pass the drawer
        drawerContent = {
            Drawer()
        },

    isFloatingActionButtonDocked = true,
        floatingActionButton = {
            // Create a floating action button in
            // floatingActionButton parameter of scaffold

            FloatingActionButton(
                modifier = Modifier
                    .border(2.dp,MaterialTheme.colors.secondary, CircleShape),
                backgroundColor= Color(color = 0xFF1E88E5),
                        onClick = {
                                 navHostController.navigate(NavRoute.AddTaskScreen.route)
                        },
            ) {
                // Simple Text inside FAB
                Text(text = "+")
            }


        }
    )

}
