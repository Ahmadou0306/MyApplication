package com.example.myapplication.projetmobile.viewsmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.projetmobile.Graph
import com.example.myapplication.projetmobile.dataSource.models.Member
import com.example.myapplication.projetmobile.dataSource.models.Project
import com.example.myapplication.projetmobile.dataSource.models.SousProject
import com.example.myapplication.projetmobile.repository.MemberRepository
import com.example.myapplication.projetmobile.repository.ProjectRepository
import com.example.myapplication.projetmobile.repository.SubProjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch


class SubProjectViewModel(private val subProjectDataSource: SubProjectRepository = Graph.subProjectRepo): ViewModel() {
    private val _state = MutableStateFlow(SubProjectViewState())
    val state: StateFlow<SubProjectViewState>
        get() = _state

    private val subProjectList = subProjectDataSource.readAllSubProject
    private val memberSelected = MutableStateFlow(_state.value.memberSelected)
    init {
        viewModelScope.launch {
            combine(subProjectList, memberSelected) { list: List<SousProject>, selected: Boolean ->
                SubProjectViewState(list, selected)
            }.collect {
                _state.value = it
            }
        }
    }
    fun addSubProject(project: SousProject) = viewModelScope.launch {
        subProjectDataSource.addSubProject(project)
    }
    fun deleteSubProject(project: SousProject) = viewModelScope.launch {
        subProjectDataSource.deleteSubProject(project)
    }
    data class SubProjectViewState(
        val subProjectList: List<SousProject> = emptyList(),
        val memberSelected: Boolean = false,
    )
}