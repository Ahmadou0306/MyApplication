package com.example.myapplication.projetmobile.dataSource.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "notification",
    foreignKeys = [ForeignKey(entity = Project::class,
        parentColumns = ["id"],
        childColumns = ["idProject"],
        onDelete = ForeignKey.CASCADE)]
)
data class Notification(
    @PrimaryKey(autoGenerate = true) val id: Int=0,

    @ColumnInfo(name = "idProject")
    val idProject: Int,

    @ColumnInfo(name = "message")
    val message: String,
)
