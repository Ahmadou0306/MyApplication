package com.example.myapplication.projetmobile.viewsmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.projetmobile.Graph
import com.example.myapplication.projetmobile.dataSource.models.TaskToMember
import com.example.myapplication.projetmobile.repository.TaskToMemberRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class TaskToMemberViewModel(private val TaskToMemberDataSource: TaskToMemberRepository = Graph.TaskToMemberRepo): ViewModel() {

    private val _state = MutableStateFlow(TaskToMemberViewState())

    val state: StateFlow<TaskToMemberViewState>
        get() = _state

    private val taskList = TaskToMemberDataSource.readAllData
    private val selected = MutableStateFlow(_state.value.selected)

    init {
        viewModelScope.launch {
            combine(taskList, selected) { list: List<TaskToMember>, selected: Boolean ->
                TaskToMemberViewState(list, selected)
            }.collect {
                _state.value = it
            }
        }
    }

    fun add(taskToMember: TaskToMember) = viewModelScope.launch {
        TaskToMemberDataSource.addTaskToMember(taskToMember)
    }
    fun delete(taskToMember: TaskToMember) = viewModelScope.launch {
        TaskToMemberDataSource.deleteTaskToMember(taskToMember)
    }

    data class TaskToMemberViewState(
        val taskList: List<TaskToMember> = emptyList(),
        val selected: Boolean = false,
    )
}
