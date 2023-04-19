package com.example.myapplication.projetmobile.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.projetmobile.NavRoute
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.projetmobile.ui.home.componant.Drawer
import com.example.myapplication.projetmobile.ui.home.componant.Menu
import com.example.myapplication.projetmobile.ui.home.componant.barNavigation
import kotlinx.coroutines.launch

class MyAppHome: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                }
            }
        }
    }
}



data class Projects(
    val id: String,
    val name: String,
    val description: String,
    val deadline: String,
    val status: String
)
val projectsItem = listOf(
    Projects(
        id = "1",
        name = "Projet Messi",
        description = "Description du projet A",
        deadline = "01/06/2023",
        status = "En cours"
    ),
    Projects(
        id = "2",
        name = "Creation Application Mobile Kotlin",
        description = "Description du projet B",
        deadline = "30/09/2023",
        status = "En attente"
    ),
    Projects(
        id = "3",
        name = "Preparation Magal",
        description = "Description du projet C",
        deadline = "15/12/2023",
        status = "Terminé"
    )
)
@Composable
private fun ProjectsList() {
    LazyColumn {
        items(projectsItem) { project ->
            ProjectCard(project)
        }
    }
}

@Composable
private fun ProjectCard(project: Projects) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White,
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = project.name,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = Color(color = colorPersonnel)
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (isExpanded) {
                Text(
                    text = project.description,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Deadline : Mansour",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = "Status : En cour",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        modifier = Modifier
                            .background(Color(color = colorPersonnel))
                            .border(2.dp, color = Color.White, CircleShape)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .shadow(8.dp),
                        onClick = {
                            //envoie dans un autre lien
                        },

                        content = {
                            Text(
                                text = "More",
                                style = MaterialTheme.typography.button,
                                color = Color.White,
                            )
                        }
                    )
                }
            } else {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home(navHostController: NavHostController) {

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
        // pass the topbar we created
      /*  topBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)){
               onMenuCliked= Navigation(onMenuCliked).barNavigation()



            //.barNavigation { Drawer().drawerView() }
            }
        },*/

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
            ProjectsList()
                  },
        // pass the drawer
        drawerContent = {
            Drawer().drawerView()
        },
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            // Create a floating action button in
            // floatingActionButton parameter of scaffold
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(NavRoute.AddTaskScreen.route)
                },
                backgroundColor = Color(color = colorPersonnel)) {
                // Simple Text inside FAB
                Text(text = "+", color = Color.White)
            }
        },

        // pass the bottomBar
        // we created
        bottomBar = { Menu().bottom_bar()}

    )
}





