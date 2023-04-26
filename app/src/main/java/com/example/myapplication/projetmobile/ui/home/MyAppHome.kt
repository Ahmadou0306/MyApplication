package com.example.myapplication.projetmobile.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.projetmobile.dataSource.models.Project
import com.example.myapplication.projetmobile.ui.componant.EmptyContentListProject
import com.example.myapplication.projetmobile.ui.home.componant.BottomBar
import com.example.myapplication.projetmobile.ui.home.componant.FloatingActionButtonComp
import com.example.myapplication.projetmobile.ui.home.componant.ProjectCard
import com.example.myapplication.projetmobile.ui.home.componant.barNavigation
import com.example.myapplication.projetmobile.ui.home.componant.drawerView
import com.example.myapplication.projetmobile.viewsmodels.ProjectViewModel
import kotlinx.coroutines.launch





@Composable
private fun ProjectsList(
    onNavigate: (Project?) -> Unit
) {
    val viewModel = viewModel(ProjectViewModel::class.java)
    val state by viewModel.state.collectAsState()
    LazyColumn {
        if(state.projectList.isNotEmpty()){
            items(state.projectList) { project ->
                ProjectCard(project = project,
                    onNavigation ={onNavigate(it)},
                )
            }
        }else{
            item {
                EmptyContentListProject()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home(
    onNavigate: (Project?) -> Unit,
    onHomeNavigate:()->Unit
) {

    // create a scaffold state, set it to close by default
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))



    // Create a coroutine scope. Opening of
    // Drawer and snackbar should happen in
    // background thread without blocking main thread

    val coroutineScope= rememberCoroutineScope()

    // Scaffold Composable
    Scaffold(
        // pass the scaffold state
        scaffoldState = scaffoldState,

        topBar = {
                barNavigation (onMenuClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
                )
            },

        // Pass the body in
        // content parameter
        content = {
            ProjectsList(onNavigate)
                  },
        // pass the drawer
        drawerContent = {
            drawerView()
        },
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            // Create a floating action button in
            // floatingActionButton parameter of scaffold
            FloatingActionButtonComp(onNavigate)
        },

        // pass the bottomBar
        // we created
        bottomBar = { BottomBar(onHomeNavigate)}

    )
}





