package com.example.myapplication.projetmobile.dataSource.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project")
data class Project(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "chefName")
    val chefName: String,
    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "dueDate")
    val dueDate: String,

    @ColumnInfo(name = "dueDateFin")
    val dueDateFin: String,

    @ColumnInfo(name = "isCompleted")
    var isCompleted: Boolean=false,

)