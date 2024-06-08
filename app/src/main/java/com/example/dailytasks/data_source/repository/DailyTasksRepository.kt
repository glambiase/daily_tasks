package com.example.dailytasks.data_source.repository

import com.example.dailytasks.data_source.local.DailyTasksDao
import com.example.dailytasks.data_source.local.entity.DailyTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class DailyTasksRepository @Inject constructor(
    private val dailyTasksDao: DailyTasksDao
) {
    fun getAllTasks(): Flow<List<DailyTask>> = dailyTasksDao.getAllTasks()

    fun getSelectedTask(taskId: Int): Flow<DailyTask> = dailyTasksDao.getSelectedTask(taskId)

    suspend fun addTask(task: DailyTask) = dailyTasksDao.addTask(task)

    suspend fun updateTask(task: DailyTask) = dailyTasksDao.updateTask(task)

    suspend fun deleteTask(task: DailyTask) = dailyTasksDao.deleteTask(task)

    suspend fun deleteAllTasks() = dailyTasksDao.deleteAllTasks()

    fun searchDB(query: String) = dailyTasksDao.searchDB(query)

    fun sortTasksByDoneStatus(): Flow<List<DailyTask>> = dailyTasksDao.sortTasksByDoneStatus()

    fun sortTasksByToDoStatus(): Flow<List<DailyTask>> = dailyTasksDao.sortTasksByToDoStatus()
}