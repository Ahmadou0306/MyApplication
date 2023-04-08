package com.example.myapplication.view.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class Menu: ViewModel() {

    private val colorPersonnel =0xFF1E88E5


    @Composable
    fun bottom_bar() {
        BottomAppBar(
            backgroundColor= Color(color = colorPersonnel),
            cutoutShape = MaterialTheme.shapes.small.copy(
                CornerSize(percent = 50)
            ),
        ) {

            var isIconRotated by remember {
                mutableStateOf(false)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(90.dp), modifier = Modifier.background(Color(color = colorPersonnel))) {

                Icon(
                    imageVector =  Icons.Default.Home ,
                    contentDescription ="",
                    tint = Color.White,
                    modifier= Modifier
                        .background(Color(color = colorPersonnel))
                        .width(40.dp)
                        .clickable { isIconRotated=!isIconRotated}
                        .rotate(if (isIconRotated) 45f else 0f)
                    ,
                )
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription ="",
                    modifier= Modifier
                        .background(Color(color = colorPersonnel))
                        .width(40.dp)
                        .clickable { isIconRotated=!isIconRotated}
                        .rotate(if (isIconRotated) 45f else 0f)                    ,
                    tint = Color.White,
                )
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription ="",
                    modifier= Modifier
                        .background(Color(color = colorPersonnel))
                        .width(40.dp)
                        .clickable { isIconRotated=!isIconRotated}
                        .rotate(if (isIconRotated) 45f else 0f),
                    tint = Color.White,
                )
            }

        }
    }
}