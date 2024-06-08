package com.example.dailytasks.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dailytasks.data_source.local.entity.DailyTask

@Database(entities = [DailyTask::class], version = 1)
abstract class DailyTasksDatabase : RoomDatabase() {
    abstract fun dailyTasksDao(): DailyTasksDao
}