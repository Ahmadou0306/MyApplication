package com.example.myapplication.projetmobile

import android.content.Context
import com.example.myapplication.projetmobile.dataSource.ProjectDataBase
import com.example.myapplication.projetmobile.repository.MemberRepository
import com.example.myapplication.projetmobile.repository.ProjectRepository
import com.example.myapplication.projetmobile.repository.SubProjectRepository
import com.example.myapplication.projetmobile.repository.TaskRepository

object Graph {
     private lateinit var dataBase: ProjectDataBase
    val taskRepo by lazy {
        TaskRepository(dataBase.taskDao())
    }

    val memberRepo by lazy {
        MemberRepository(dataBase.memberDao())
    }
    val projectRepo by lazy {
        ProjectRepository(dataBase.projectDao())
    }

    val subProjectRepo by lazy {
        SubProjectRepository(dataBase.subProjectDao())
    }
    fun provide(context: Context) {
        dataBase = ProjectDataBase.getDatabase(context)
    }
}