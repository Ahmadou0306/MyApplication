package com.example.myapplication.projetmobile.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.projetmobile.dataSource.TaskDao
import com.example.myapplication.projetmobile.models.Task


class TaskRepository(private val taskDao: TaskDao) {
    val readAllData : LiveData<List<Task>> =  taskDao.getAll()
    suspend fun addTask(todoItem: Task) {
        taskDao.insert(todoItem)
    }

    suspend fun deleteTodo(todoItem: Task) {
        taskDao.delete(todoItem)
    }

}