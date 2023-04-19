package com.example.myapplication.projetmobile.viewsmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.projetmobile.Graph
import com.example.myapplication.projetmobile.dataSource.models.Task
import com.example.myapplication.projetmobile.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDataSource:TaskRepository=Graph.taskRepo):ViewModel() {
    private val _state = MutableStateFlow(TaskViewState())
    val state: StateFlow<TaskViewState>
        get() = _state

     val taskList = taskDataSource.readAllData
    private val selected = MutableStateFlow(_state.value.selected)
    init {
        viewModelScope.launch {
            combine(taskList, selected) { list: List<Task>, selected: Boolean ->
                TaskViewState(list, selected)
            }.collect {
                _state.value = it
            }
        }
    }
    fun add(task: Task) = viewModelScope.launch {
        taskDataSource.addTask(task)
    }
    fun delete(task: Task) = viewModelScope.launch {
        taskDataSource.deleteTask(task)
    }
    data class TaskViewState(
        val taskList: List<Task> = emptyList(),
        val selected: Boolean = false,
    )
}