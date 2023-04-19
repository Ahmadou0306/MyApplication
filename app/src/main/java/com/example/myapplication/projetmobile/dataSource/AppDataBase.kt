package com.example.myapplication.projetmobile.dataSource

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.projetmobile.dataSource.dao.MemberDao
import com.example.myapplication.projetmobile.dataSource.models.Member
import com.example.myapplication.projetmobile.dataSource.models.Task

@Database(entities = [Task::class,Member::class], version = 1, exportSchema = false)
abstract class ProjectDataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun memberDao(): MemberDao
    companion object {
        @Volatile
        private var INSTANCE: ProjectDataBase? = null
        fun getDatabase(context: Context): ProjectDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProjectDataBase::class.java,
                    "project_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
