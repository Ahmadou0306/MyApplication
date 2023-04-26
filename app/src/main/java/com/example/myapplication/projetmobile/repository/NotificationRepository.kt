package com.example.myapplication.projetmobile.repository

import com.example.myapplication.projetmobile.dataSource.dao.NotificationDao
import com.example.myapplication.projetmobile.dataSource.models.Notification
import kotlinx.coroutines.Dispatchers

class NotificationRepository(private val notificationDao: NotificationDao) {
    val readAllNotification =  notificationDao.getAllTNotification()
    suspend fun addNotification(notification: Notification) {
        Dispatchers.IO.apply {
            notificationDao.insert(notification)
        }
    }

    suspend fun deleteNotification(notification: Notification) {
        Dispatchers.IO.apply {
            notificationDao.deleteNotification(notification.id)
        }
    }
}