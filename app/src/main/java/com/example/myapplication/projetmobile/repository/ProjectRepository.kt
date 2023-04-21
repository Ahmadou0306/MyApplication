package com.example.myapplication.projetmobile.repository

import com.example.myapplication.projetmobile.dataSource.dao.ProjectDao
import com.example.myapplication.projetmobile.dataSource.models.Project
import kotlinx.coroutines.Dispatchers

class ProjectRepository(private val projectDao: ProjectDao) {
    val readAllProject =  projectDao.getAllProject()
    suspend fun addProject(project: Project) {
        Dispatchers.IO.apply {
            projectDao.insertProject(project)
        }
    }

    suspend fun deleteProject(project: Project) {
        Dispatchers.IO.apply {
            projectDao.deleteProject(project.id)
        }
    }

}