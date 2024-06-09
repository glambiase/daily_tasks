package com.glambiase.dailytasks.domain.repository

import com.glambiase.dailytasks.domain.model.DailyTask
import kotlinx.coroutines.flow.Flow

interface DailyTasksRepository {

    fun getAllTasks(): Flow<List<DailyTask>>

    fun getSelectedTask(taskId: Int): Flow<DailyTask>

    suspend fun addTask(task: DailyTask)

    suspend fun updateTask(task: DailyTask)

    suspend fun deleteTask(task: DailyTask)

    suspend fun deleteAllTasks()

    fun searchDB(query: String): Flow<List<DailyTask>>

    fun sortTasksByDoneStatus(): Flow<List<DailyTask>>

    fun sortTasksByToDoStatus(): Flow<List<DailyTask>>
}