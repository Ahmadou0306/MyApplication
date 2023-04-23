package com.example.myapplication.projetmobile.repository

import com.example.myapplication.projetmobile.dataSource.dao.TaskToMemberDao
import com.example.myapplication.projetmobile.dataSource.models.TaskToMember
import kotlinx.coroutines.Dispatchers


class TaskToMemberRepository(private val taskToMemberDao: TaskToMemberDao){

    val readAllData =  taskToMemberDao.getAllTaskToMember()

    suspend fun addTaskToMember(taskToMember: TaskToMember) {
        Dispatchers.IO.apply {
            taskToMemberDao.insert(taskToMember)
        }
    }
    suspend fun deleteTaskToMember(taskToMember: TaskToMember) {
        Dispatchers.IO.apply {
            taskToMemberDao.deleteTaskToMember(taskToMember.id)
        }
    }

}