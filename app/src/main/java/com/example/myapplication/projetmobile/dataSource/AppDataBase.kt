package com.example.myapplication.projetmobile.dataSource

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.projetmobile.models.Task

@Database(entities = [Task::class],version = 1)
abstract class TaskDatabase: RoomDatabase() {

    abstract fun  taskDAO() : TaskDao

    companion object{

        private var INSTANCE : TaskDatabase? = null
        fun getInstance(application: Application): TaskDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        application.applicationContext,
                        TaskDatabase::class.java,
                        "todo_list_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE=instance
                }
                return instance
            }
        }

    }
}
