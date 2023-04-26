package com.example.myapplication.projetmobile.viewsmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.projetmobile.Graph
import com.example.myapplication.projetmobile.dataSource.models.Notification
import com.example.myapplication.projetmobile.repository.NotificationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch


class NotificationViewModel(private val notificationDao: NotificationRepository = Graph.NotificationRepo): ViewModel() {
    private val _state = MutableStateFlow(NotificationViewState())
    val state: StateFlow<NotificationViewState>
        get() = _state

    private val notificationList = notificationDao.readAllNotification
    private val memberSelected = MutableStateFlow(_state.value.memberSelected)
    init {
        viewModelScope.launch {
            combine(notificationList, memberSelected) { list: List<Notification>, selected: Boolean ->
                NotificationViewState(list, selected)
            }.collect {
                _state.value = it
            }
        }
    }
    fun addNotification(notification: Notification) = viewModelScope.launch {
        notificationDao.addNotification(notification)
    }
    fun deleteNotification(notification: Notification?) = viewModelScope.launch {
        if (notification != null) {
            notificationDao.deleteNotification(notification)
        }
    }

    data class NotificationViewState(
        val notificationList: List<Notification> = emptyList(),
        val memberSelected: Boolean = false,
    )
}