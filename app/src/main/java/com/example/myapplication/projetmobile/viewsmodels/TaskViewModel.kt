package com.example.myapplication.projetmobile.viewsmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.projetmobile.dataSource.TaskDatabase
import com.example.myapplication.projetmobile.models.Task
import com.example.myapplication.projetmobile.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(application: Application):AndroidViewModel(application) {
    private val repository:TaskRepository

     var allTasks:LiveData<List<Task>>
    init {
        val  taskDao=TaskDatabase.getInstance(application).taskDAO()
       repository=TaskRepository(taskDao)
        allTasks=repository.readAllData
    }

    fun addTask(task: Task){
        viewModelScope.launch {
            repository.addTask(task)
        }
    }
}