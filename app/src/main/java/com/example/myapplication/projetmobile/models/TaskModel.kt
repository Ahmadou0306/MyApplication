package com.example.myapplication.projetmobile.models



data class TaskModel (
    val id: Int=0,
    val name: String,
    val description: String,
    val dueDate: String,
    var isCompleted: Boolean,
    var assignedMembers: String="",
)