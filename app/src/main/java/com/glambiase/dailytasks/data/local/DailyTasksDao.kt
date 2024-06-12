package com.glambiase.dailytasks.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyTasksDao {

    @Query("SELECT * FROM dailytaskentity ORDER BY id DESC")
    fun getAllTasks(): Flow<List<DailyTaskEntity>>

    @Query("SELECT * FROM dailytaskentity WHERE id=:taskId")
    suspend fun getTaskById(taskId: Int): DailyTaskEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(dailyTask: DailyTaskEntity)

    @Delete
    suspend fun deleteTask(dailyTask: DailyTaskEntity)

    @Query("DELETE FROM dailytaskentity")
    suspend fun deleteAllTasks()
}