package com.example.myapplication.projetmobile.dataSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.projetmobile.dataSource.dao.MemberDao
import com.example.myapplication.projetmobile.dataSource.dao.ProjectDao
import com.example.myapplication.projetmobile.dataSource.dao.SubProjectDao
import com.example.myapplication.projetmobile.dataSource.dao.TaskToMemberDao
import com.example.myapplication.projetmobile.dataSource.models.Member
import com.example.myapplication.projetmobile.dataSource.models.Project
import com.example.myapplication.projetmobile.dataSource.models.SousProject
import com.example.myapplication.projetmobile.dataSource.models.Task
import com.example.myapplication.projetmobile.dataSource.models.TaskToMember

@Database(entities = [Task::class, Member::class, Project::class,SousProject::class,TaskToMember::class], version = 10, exportSchema = false)
abstract class ProjectDataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun memberDao(): MemberDao
    abstract fun projectDao(): ProjectDao
    abstract fun subProjectDao(): SubProjectDao
    abstract fun subTaskToMember(): TaskToMemberDao
    companion object {
        @Volatile
        private var INSTANCE: ProjectDataBase? = null

        fun getDatabase(context: Context): ProjectDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProjectDataBase::class.java,
                    "project_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}