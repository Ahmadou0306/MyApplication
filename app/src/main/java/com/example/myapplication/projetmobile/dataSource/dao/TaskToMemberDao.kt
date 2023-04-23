package com.example.myapplication.projetmobile.dataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.projetmobile.dataSource.models.TaskToMember
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskToMemberDao {
    @Query("SELECT * FROM taskToMember")
    fun getAllTaskToMember(): Flow<List<TaskToMember>>

    @Insert
    suspend fun insert( taskToMember: TaskToMember)

    @Query("Delete From taskToMember WHERE id = :id")
    suspend fun deleteTaskToMember(id: Int)


}