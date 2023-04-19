package com.example.myapplication.projetmobile

import android.content.Context
import com.example.myapplication.projetmobile.dataSource.ProjectDataBase
import com.example.myapplication.projetmobile.repository.MemberRepository
import com.example.myapplication.projetmobile.repository.TaskRepository

object Graph {
     private lateinit var dataBase: ProjectDataBase
    val taskRepo by lazy {
        TaskRepository(dataBase.taskDao())
    }

    val memberRepo by lazy {
        MemberRepository(dataBase.memberDao())
    }

    fun provide(context: Context) {
        dataBase = ProjectDataBase.getDatabase(context)
    }
}