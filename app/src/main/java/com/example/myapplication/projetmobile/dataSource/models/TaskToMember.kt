package com.example.myapplication.projetmobile.dataSource.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "taskToMember",
    foreignKeys = [
        ForeignKey(entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["idTask"],
            onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Member::class,
            parentColumns = ["id"],
            childColumns = ["idMember"],
            onDelete = ForeignKey.CASCADE)
    ])
data class TaskToMember(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "idTask") val idTask: Int,
    @ColumnInfo(name = "idMember") val idMember: Int,
)