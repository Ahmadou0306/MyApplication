package com.example.myapplication.projetmobile.dataSource.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo(name = "idProject")
    val idProject: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "dueDate")
    val dueDate: String,
    @ColumnInfo(name = "finDate")
    val finDate: String,
    @ColumnInfo(name = "isCompleted")
    var isCompleted: Boolean=false,
)
