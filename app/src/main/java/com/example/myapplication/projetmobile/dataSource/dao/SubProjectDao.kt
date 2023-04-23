package com.example.myapplication.projetmobile.dataSource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.projetmobile.dataSource.models.Project
import com.example.myapplication.projetmobile.dataSource.models.SousProject
import com.example.myapplication.projetmobile.dataSource.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface SubProjectDao {
    @Query("SELECT * FROM subproject")
    fun getAllSubProject(): Flow<List<SousProject>>

    @Insert
    suspend fun insert( subProject: SousProject)

    @Query("Delete From subproject WHERE id = :id")
    suspend fun deleteProject(id: Int)


}