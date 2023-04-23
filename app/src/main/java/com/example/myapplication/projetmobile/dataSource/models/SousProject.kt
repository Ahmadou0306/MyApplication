package com.example.myapplication.projetmobile.dataSource.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "subproject",
    foreignKeys = [ForeignKey(entity = Project::class,
        parentColumns = ["id"],
        childColumns = ["idProject"],
        onDelete = ForeignKey.CASCADE)]
    )
data class SousProject(
    @PrimaryKey(autoGenerate = true) val id: Int=0,

    @ColumnInfo(name = "idProject")
    val idProject: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "dueDate")
    val dueDate: String,

    @ColumnInfo(name = "dueDateFin")
    val dueDateFin: String,
)