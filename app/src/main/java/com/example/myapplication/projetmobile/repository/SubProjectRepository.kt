package com.example.myapplication.projetmobile.repository

import com.example.myapplication.projetmobile.dataSource.dao.ProjectDao
import com.example.myapplication.projetmobile.dataSource.dao.SubProjectDao
import com.example.myapplication.projetmobile.dataSource.models.Project
import com.example.myapplication.projetmobile.dataSource.models.SousProject
import kotlinx.coroutines.Dispatchers


class SubProjectRepository(private val subProjectDao: SubProjectDao) {
    val readAllSubProject =  subProjectDao.getAllSubProject()
    suspend fun addSubProject(subProject: SousProject) {
        Dispatchers.IO.apply {
            subProjectDao.insert(subProject)
        }
    }

    suspend fun deleteSubProject(subProject: SousProject) {
        Dispatchers.IO.apply {
            subProjectDao.deleteProject(subProject.id)
        }
    }

}