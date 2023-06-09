package com.example.myapplication.projetmobile.dataSource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.projetmobile.dataSource.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
 interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): Flow<List<Task>>

    @Insert
    suspend fun insertAll(vararg task: Task)

    @Insert
    suspend fun insert( task: Task)

    @Update
    suspend fun update(task:Task)

   @Query("Delete From task WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM task WHERE idProject=:id")
    fun getTasksByIdProject(id: Int):Flow<List<Task>>
}