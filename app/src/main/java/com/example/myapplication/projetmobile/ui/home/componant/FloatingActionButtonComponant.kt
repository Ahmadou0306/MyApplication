package com.example.myapplication.projetmobile.ui.home.componant

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.projetmobile.dataSource.models.Project

private const val colorPersonnel=0xFF1E88E5

@Composable
fun FloatingActionButtonComp(onNavigate: (Project?) -> Unit) {
    androidx.compose.material.FloatingActionButton(
        onClick = {
            onNavigate(null)
        },
        modifier = Modifier
            .border(2.dp, MaterialTheme.colors.secondary, CircleShape),
        backgroundColor = Color(color = colorPersonnel)
    ) {
        // Simple Text inside FAB
        Text(text = "+", color = Color.White)
    }
}