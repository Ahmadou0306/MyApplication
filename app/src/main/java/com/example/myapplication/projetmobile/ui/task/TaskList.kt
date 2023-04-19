package com.example.myapplication.projetmobile.ui.task

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.projetmobile.ui.detailsProject.TaskCard3
import com.example.myapplication.projetmobile.viewsmodels.TaskViewModel

@Composable
fun taskListScreen() {
    val viewModel = viewModel(TaskViewModel::class.java)
    val state by viewModel.state.collectAsState()

    LazyColumn {
        items(state.taskList) { task ->
            TaskCard3(task)
        }
    }
}