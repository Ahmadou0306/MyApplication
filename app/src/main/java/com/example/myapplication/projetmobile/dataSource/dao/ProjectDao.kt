package com.example.myapplication.projetmobile.dataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.projetmobile.dataSource.models.Project
import kotlinx.coroutines.flow.Flow



@Dao
interface ProjectDao {
    @Query("SELECT * FROM project")
    fun getAllProject(): Flow<List<Project>>

    @Insert
    suspend fun insertProject( project: Project)

    @Query("Delete From project WHERE id = :id")
    suspend fun deleteProject(id: Int)
}