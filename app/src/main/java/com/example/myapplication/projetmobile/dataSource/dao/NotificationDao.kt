package com.example.myapplication.projetmobile.dataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.projetmobile.dataSource.models.Notification
import com.example.myapplication.projetmobile.dataSource.models.TaskToMember
import kotlinx.coroutines.flow.Flow



@Dao
interface NotificationDao {
    @Query("SELECT * FROM notification")
    fun getAllTNotification(): Flow<List<Notification>>

    @Insert
    suspend fun insert( notification: Notification)

    @Query("Delete From notification WHERE id = :id")
    suspend fun deleteNotification(id: Int)


}