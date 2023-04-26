package com.example.myapplication.projetmobile.repository

import com.example.myapplication.projetmobile.dataSource.TaskDao
import com.example.myapplication.projetmobile.dataSource.models.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow


class TaskRepository(private val taskDao: TaskDao) {

    val readAllData =  taskDao.getAll()

    fun listTasGetByIdProject(id:Int)=taskDao.getTasksByIdProject(id)
    suspend fun addTask(task: Task) {
        Dispatchers.IO.apply {
            taskDao.insert(task)
        }
    }
    fun listTask(id:Int):Flow<List<Task>>{
        return taskDao.getTasksByIdProject(id)
    }

    suspend fun deleteTask(task: Task) {
        Dispatchers.IO.apply {
            taskDao.delete(task.id)
        }
    }
    suspend fun updateTask(task: Task) {
        Dispatchers.IO.apply {
            taskDao.update(task)
        }
    }

}