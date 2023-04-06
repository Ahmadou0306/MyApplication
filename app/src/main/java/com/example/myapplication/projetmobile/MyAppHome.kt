package com.example.myapplication.projetmobile

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.myapplication.ui.theme.MyApplicationTheme

class MyAppHome: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    ScaffoldExamples()

                }
                
            }
        }
    }
}



@Composable
fun Navigation() {
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
                    color= Color.White,
                    modifier = Modifier.padding(horizontal = 50.dp)
                )
            },
            backgroundColor= Color(color = 0xFF1E88E5)
        )
}
@Preview
@Composable
fun container() {
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        LazyColumn(){
            var i:Int = 0
            items(1){
                while(i<20){
                    Box(modifier = Modifier
                        .width(250.dp)
                        .padding(vertical = 3.dp)
                        .border(2.dp, Color.Black)
                        .background(color = Color.LightGray)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .padding(vertical = 16.dp),

                        Alignment.Center,

                        ) {
                        Text(text = "Projet $i", style = TextStyle(fontWeight = FontWeight.Bold))
                    }
                    i++
                }
            }
        }




    }
}



@Composable
fun bottom_bar() {
    BottomAppBar(
        backgroundColor= Color(color = 0xFF1E88E5),
        cutoutShape = MaterialTheme.shapes.small.copy(
            CornerSize(percent = 50)
        )
    ) {

        Text(
            text = "Add",
            color = Color.White,
            textAlign = TextAlign.Center
        )

    }
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldExamples() {

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
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)){
                TopBarMenu()
            }
        },

        // pass the bottomBar
        // we created
        bottomBar = { BottomBar() },

        // Pass the body in
        // content parameter
        content = { container() },

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
        })}