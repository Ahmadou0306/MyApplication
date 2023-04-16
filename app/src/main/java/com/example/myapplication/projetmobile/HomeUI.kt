package com.example.myapplication.projetmobile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class HomeUI :ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    ScaffoldExample()
                }
            }
        }
    }
}
@Composable
fun TopBarMenu(){
    TopAppBar(
        navigationIcon = {
                         Icon(
                             imageVector = Icons.Default.Menu,
                             contentDescription ="Menu"
                         )
        },
        title = {
            Text(
                text = "TeamFlow",
                color=Color.White,
                modifier = Modifier.padding(horizontal = 50.dp)
            )
        },
        backgroundColor= Color(color = 0xFF1E88E5)

    )
}

@Preview(showBackground =true )
@Composable
fun BottomBar(){
    BottomAppBar(
        backgroundColor= Color(color = 0xFF1E88E5),
        cutoutShape = MaterialTheme.shapes.small.copy(
        CornerSize(percent = 50)
    )
    ) {

    Text(text = "", color = Color.White)

    }
}


@Composable
fun Drawer() {
    // Column Composable
    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        // Repeat is a loop which
        // takes count as argument
        repeat(5) { item ->
            Text(text = "Item number $item", modifier = Modifier.padding(8.dp), color = Color.Black)
        }
    }
}


@Composable
fun Body() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(text = "Aucun Projet", color = Color(0xFF1E88E5))
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldExample() {

    // create a scaffold state, set it to close by default
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    // Create a coroutine scope. Opening of
    // Drawer and snackbar should happen in
    // background thread without blocking main thread

    // Scaffold Composable
    Scaffold(

        // pass the scaffold state
        scaffoldState = scaffoldState,

        // pass the topbar we created
        topBar = {
            TopBarMenu()
        },

        // pass the bottomBar
        // we created
        bottomBar = { BottomBar() },

        // Pass the body in
        // content parameter
        content = { Body()},

        // pass the drawer
        drawerContent = {
            Drawer()
        },
    isFloatingActionButtonDocked = true,
        floatingActionButton = {
            // Create a floating action button in
            // floatingActionButton parameter of scaffold
            FloatingActionButton(

                onClick = {

                }) {
                // Simple Text inside FAB
                Text(text = "X")
            }
        }
    )
}
