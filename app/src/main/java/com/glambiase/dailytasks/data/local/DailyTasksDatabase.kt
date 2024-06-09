package com.glambiase.dailytasks.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DailyTaskEntity::class], version = 1)
abstract class DailyTasksDatabase : RoomDatabase() {
    abstract fun dailyTasksDao(): DailyTasksDao
}