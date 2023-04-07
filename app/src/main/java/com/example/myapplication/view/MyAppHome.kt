package com.example.myapplication.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.view.layout.Drawer
import com.example.myapplication.view.layout.Menu
import com.example.myapplication.view.layout.Navigation

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
private fun container() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        LazyColumn(){
            var i:Int = 0
            //utiliser une liste pour les items
            items(1){
                while(i<20){
                    Box(modifier = Modifier
                        .width(250.dp)
                        .clickable(
                            enabled = true,
                            onClick = { println("$i++") },
                        )
                        .padding(vertical = 23.dp)
                        .clip(shape = RoundedCornerShape(30.dp))
                        .border(3.dp, Color.Blue)
                        .background(Color(color = 0xFF1E88E5))
                        .padding(50.dp),
                        Alignment.Center,

                        ) {
                        Text(text = "Projet $i",color = Color.White, style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            textDecoration = TextDecoration.Underline
                        ),
                        )
                    }
                    i++
                }
            }
        }




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
                Navigation().barNavigation()
            }
        },
        // Pass the body in
        // content parameter
        content = {container() },
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

                }) {
                // Simple Text inside FAB
                Text(text = "X")
            }
        },

        // pass the bottomBar
        // we created
        bottomBar = { Menu().bottom_bar()}

    )
}




