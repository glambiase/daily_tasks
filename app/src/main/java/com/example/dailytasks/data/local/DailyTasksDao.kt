package com.example.dailytasks.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyTasksDao {

    @Query("SELECT * FROM dailytaskentity ORDER BY id ASC")
    fun getAllTasks(): Flow<List<DailyTaskEntity>>

    @Query("SELECT * FROM dailytaskentity WHERE id=:taskId")
    fun getSelectedTask(taskId: Int): Flow<DailyTaskEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(dailyTask: DailyTaskEntity)

    @Update
    suspend fun updateTask(dailyTask: DailyTaskEntity)

    @Delete
    suspend fun deleteTask(dailyTask: DailyTaskEntity)

    @Query("DELETE FROM dailytaskentity")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM dailytaskentity WHERE title LIKE :query OR description LIKE :query")
    fun searchDB(query: String): Flow<List<DailyTaskEntity>>

    @Query(
        """
        SELECT * FROM dailytaskentity ORDER BY
    CASE
        WHEN status LIKE 'D%' THEN 1
        WHEN status LIKE 'I%' THEN 2
        WHEN status LIKE 'T%' THEN 3
    END
    """
    )
    fun sortTasksByDoneStatus(): Flow<List<DailyTaskEntity>>

    @Query(
        """
        SELECT * FROM dailytaskentity ORDER BY
    CASE
        WHEN status LIKE 'T%' THEN 1
        WHEN status LIKE 'I%' THEN 2
        WHEN status LIKE 'D%' THEN 3
    END
    """
    )
    fun sortTasksByToDoStatus(): Flow<List<DailyTaskEntity>>
}