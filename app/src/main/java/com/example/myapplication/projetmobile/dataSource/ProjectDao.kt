package com.example.myapplication.projetmobile.dataSource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.projetmobile.models.Project

@Dao
interface ProjectDao {
    @Query("SELECT * FROM project")
    fun getAll():List<Project>

    @Insert
    fun insertAll(vararg project: Project)

    @Delete
    fun delete(project: Project)
}