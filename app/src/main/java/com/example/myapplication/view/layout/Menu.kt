package com.example.myapplication.view.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class Menu: ViewModel() {
    @Composable
    fun bottom_bar() {
        BottomAppBar(
            backgroundColor= Color(color = 0xFF1E88E5),
            cutoutShape = MaterialTheme.shapes.small.copy(
                CornerSize(percent = 50)
            ),
            modifier = Modifier.padding(bottom = 5.dp,)
        ) {

            Text(
                text = "Add",
                color = Color.White,
                textAlign = TextAlign.Center
            )

        }
    }
}