package com.example.myapplication.projetmobile.dataSource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.projetmobile.models.Task

@Dao
 interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll():LiveData<List<Task>>

    @Insert
    suspend fun insertAll(vararg task: Task)

    @Insert
    suspend fun insert( task: Task)

    @Delete
    suspend fun delete(task: Task)
}