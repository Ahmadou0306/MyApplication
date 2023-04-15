package com.example.myapplication.model

interface ProjectDao {
    @Query("SELECT * FROM project")
    fun getAll():List<Project>

    @Insert
    fun insertAll(vararg project:Project)

    @Delete
    fun delete(project:Project)
}