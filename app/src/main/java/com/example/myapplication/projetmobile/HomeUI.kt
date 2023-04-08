@file:Suppress("UNUSED_EXPRESSION")

package com.example.myapplication.projetmobile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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
const val colorPersonnel=0xFF1E88E5


@Composable
fun TopBarMenu(onMenuCliked: () -> Unit){
    TopAppBar(
        navigationIcon = {
                         Icon(
                             imageVector = Icons.Default.Menu,
                             contentDescription ="Menu",
                             modifier = Modifier.clickable(onClick =  onMenuCliked )
                         )
        },
        title = {
            Text(
                text = "TeamFlow",
                color=Color.White,
                modifier = Modifier.padding(horizontal = 50.dp)
            )
        },
        backgroundColor= Color(color = colorPersonnel)

    )
}



@Preview(showBackground =true )
@Composable
fun BottomBar(){
    BottomAppBar(
        backgroundColor= Color(color = colorPersonnel),
        cutoutShape = MaterialTheme.shapes.small.copy(
        CornerSize(percent = 50)
    )
    ) {

    Surface {

        Column(modifier = Modifier.background(Color(color = colorPersonnel) )) {
            val iconRotations = remember {
                listOf(
                    mutableStateOf(false),
                    mutableStateOf(false),
                    mutableStateOf(false)
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(90.dp), modifier = Modifier.background(Color(color = colorPersonnel))) {
                
                Icon(
                    imageVector =  Icons.Default.Home ,
                    contentDescription ="",
                    tint = Color.White,
                    modifier= Modifier
                        .background(Color(color = colorPersonnel))
                        .width(40.dp)
                        .clickable {
                            iconRotations[0].value = !iconRotations[0].value
                        }
                        .rotate(if (iconRotations[0].value) 45f else 0f)
                    ,
                )
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription ="",
                    modifier= Modifier
                        .background(Color(color = colorPersonnel))
                        .width(40.dp)
                        .clickable {
                            iconRotations[1].value = !iconRotations[1].value
                        }
                        .rotate(if (iconRotations[1].value) 45f else 0f)                   ,
                    tint = Color.White,
                )
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription ="",
                    modifier= Modifier
                        .background(Color(color = colorPersonnel))
                        .width(40.dp)
                        .clickable {
                            iconRotations[2].value = !iconRotations[2].value
                        }
                        .rotate(if (iconRotations[2].value) 45f else 0f)
                    ,
                    tint = Color.White,
                )
            }
            LaunchedEffect(iconRotations[0].value) {
                delay(200) // Attendre une seconde
                iconRotations[0].value = false // Réinitialiser la rotation
            }

            LaunchedEffect(iconRotations[1].value) {
                delay(200) // Attendre une seconde
                iconRotations[1].value = false // Réinitialiser la rotation
            }

            LaunchedEffect(iconRotations[2].value) {
                delay(200) // Attendre une seconde
                iconRotations[2].value = false // Réinitialiser la rotation
            }
        }
    }

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
        repeat(1) { item ->
            Text(text = "Se Deconnecter $item", modifier = Modifier.padding(8.dp), color = Color.Black)
        }
    }
}

/* gthtguyi*/
@Composable
fun Body() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(text = "Empty", color = Color(0xFF1E88E5))
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldExample() {

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
                onMenuCliked = {
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
                modifier = Modifier
                    .border(2.dp,MaterialTheme.colors.secondary, CircleShape),
                backgroundColor= Color(color = 0xFF1E88E5),

                        onClick = {

                }) {
                // Simple Text inside FAB
                Text(text = "+")
            }


        }
    )

}
