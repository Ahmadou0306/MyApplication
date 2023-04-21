package com.example.myapplication.projetmobile.ui.detailsProject.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.projetmobile.dataSource.models.Member
import com.example.myapplication.projetmobile.ui.detailsProject.colorPersonnel
import com.example.myapplication.projetmobile.viewsmodels.MemberViewModel
import kotlinx.coroutines.flow.Flow


@Composable
fun MembersList(members: Flow<List<Member>>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
            ,


    ) {
       Box(
           modifier = Modifier
               .background(Color(colorPersonnel))
               .shadow(
                   elevation = 1.dp,
                   shape = RoundedCornerShape(1.dp),
                   clip = false
               )
               .padding(vertical = 3.dp)
               .fillMaxWidth()
       ) {
           Text(
               text = "Membres du projet",
               fontSize = 20.sp,
               fontWeight = FontWeight.Bold,
               modifier = Modifier
                   .fillMaxWidth()
               ,
               textAlign = TextAlign.Center,
               color = Color.White,
           )
       }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        listMember()
    }
}
@Composable
fun listMember(){
    val viewModel = viewModel(MemberViewModel::class.java)
    val state by viewModel.state.collectAsState()
    LazyColumn {
        items(state.memberList) { member ->
            memberCard(member)
        }
    }
}
@Composable
fun memberCard(member: Member) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Icone personne",
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = member.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {},
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Bouton supprimer",
                tint = Color.Red
            )
        }
    }
}
