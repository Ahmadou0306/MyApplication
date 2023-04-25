package com.example.myapplication.projetmobile.viewsmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.projetmobile.Graph
import com.example.myapplication.projetmobile.dataSource.models.Project
import com.example.myapplication.projetmobile.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch


class ProjectViewModel(private val projectDataSource: ProjectRepository = Graph.projectRepo): ViewModel() {
    private val _state = MutableStateFlow(ProjectViewState())
    val state: StateFlow<ProjectViewState>
        get() = _state

    private val projectList = projectDataSource.readAllProject
    private val memberSelected = MutableStateFlow(_state.value.memberSelected)
    init {
        viewModelScope.launch {
            combine(projectList, memberSelected) { list: List<Project>, selected: Boolean ->
                ProjectViewState(list, selected)
            }.collect {
                _state.value = it
            }
        }
    }
    fun addProject(project: Project) = viewModelScope.launch {
        projectDataSource.addProject(project = project)
    }
    fun deleteProject(project: Project) = viewModelScope.launch {
        projectDataSource.deleteProject(project)
    }
    fun getProjectById(id: Int): Flow<Project?> {
        return projectDataSource.getProjectById(id)
    }

    data class ProjectViewState(
        val projectList: List<Project> = emptyList(),
        val memberSelected: Boolean = false,
    )
}