package com.example.myapplication.projetmobile.ui.home

import android.graphics.Paint.Style
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


private const val colorPersonnel=0xFF1E88E5


@Composable
fun TopBarMenu(onMenuClicked: () -> Unit){
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription ="Menu",
                modifier = Modifier.clickable(onClick =  onMenuClicked )
            )
        },
        title = {
            Text(
                text = "TeamFlow",
                color= Color.White,
                textAlign = TextAlign.Center
            )
        },
        backgroundColor= Color(color = colorPersonnel)

    )
}



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
                Row(horizontalArrangement = Arrangement.spacedBy(90.dp), modifier = Modifier.background(
                    Color(color = colorPersonnel)
                )) {

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