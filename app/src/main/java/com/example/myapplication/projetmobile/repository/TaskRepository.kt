package com.example.myapplication.projetmobile.repository

import com.example.myapplication.projetmobile.dataSource.TaskDao
import com.example.myapplication.projetmobile.dataSource.models.Task
import kotlinx.coroutines.Dispatchers


class TaskRepository(private val taskDao: TaskDao) {
    val readAllData =  taskDao.getAll()
    suspend fun addTask(task: Task) {
        Dispatchers.IO.apply {
            taskDao.insert(task)
        }
    }

    suspend fun deleteTask(task: Task) {
        Dispatchers.IO.apply {
            taskDao.delete(task.id)
        }
    }

}