package com.example.dailytasks.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dailytasks.data.local.dto.DailyTask

@Database(entities = [DailyTask::class], version = 1)
abstract class DailyTasksDatabase : RoomDatabase() {
    abstract fun dailyTasksDao(): DailyTasksDao
}