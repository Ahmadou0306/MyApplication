package com.example.myapplication.projetmobile.dataSource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll():List<Task>

    @Insert
    fun insertAll(vararg task:Task)

    @Insert
    fun insert( task:Task)

    @Delete
    fun delete(task:Task)
}