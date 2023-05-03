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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.myapplication.projetmobile.dataSource.models.TaskToMember
import com.example.myapplication.projetmobile.ui.detailsProject.colorPersonnel
import com.example.myapplication.projetmobile.viewsmodels.MemberViewModel
import com.example.myapplication.projetmobile.viewsmodels.TaskToMemberViewModel
import kotlinx.coroutines.flow.Flow


@Composable
fun memberCard(member: Member, memberToTask: TaskToMember) {
    val viewModel = viewModel(TaskToMemberViewModel::class.java)
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
            onClick = {viewModel.delete(memberToTask) },
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
