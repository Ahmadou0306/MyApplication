package com.example.myapplication.projetmobile.ui.componant

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun EmptyContent() {
    Row( verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.padding(start = 60.dp))
        Text(
            text = "Empty",
            fontSize = 18.sp,
            fontWeight = FontWeight.Light
        )
        Image(
            painter = painterResource(R.drawable.page),
            contentDescription = "Empty content image",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}
@Composable
fun EmptyContentCalendar() {
    Row( verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.calendar),
            contentDescription = "Empty content image",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}
@Composable
fun EmptyContentListProject() {
    Row( verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.height(600.dp))
        Image(
            painter = painterResource(R.drawable.emptyfolder),
            contentDescription = "Empty content image",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}
@Composable
fun EmptyContentList() {
    Row( verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            painter = painterResource(R.drawable.emptyfolder),
            contentDescription = "Empty content image",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}



