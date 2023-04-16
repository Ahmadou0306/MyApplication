@file:Suppress("NAME_SHADOWING")

package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.projetmobile.BottomBar
import com.example.myapplication.projetmobile.Calendrier
import com.example.myapplication.projetmobile.TopBarMenu
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.util.*

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Test()
                }
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun Test() {

    Scaffold(
        topBar = { TopBarMenu() },
        content = {
            FormProject()
//                  Calendrier("2023-04-16", "2023-04-29")
        },
        bottomBar = { BottomBar()},
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                backgroundColor = Color(color = 0xFF0088FF)
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }

    )
}





