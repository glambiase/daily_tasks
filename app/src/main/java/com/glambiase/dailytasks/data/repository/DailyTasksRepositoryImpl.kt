package com.glambiase.dailytasks.data.repository

import com.glambiase.dailytasks.data.local.DailyTasksDao
import com.glambiase.dailytasks.data.mapper.toDailyTask
import com.glambiase.dailytasks.data.mapper.toDailyTaskEntity
import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.repository.DailyTasksRepository
import kotlinx.coroutines.flow.map

class DailyTasksRepositoryImpl(
    private val dailyTasksDao: DailyTasksDao
) : DailyTasksRepository {

    override fun getAllTasks() = dailyTasksDao.getAllTasks().map { taskEntities ->
        taskEntities.map {
            it.toDailyTask()
        }
    }

    override suspend fun getTaskById(taskId: Int) = dailyTasksDao.getTaskById(taskId)?.toDailyTask()

    override suspend fun insertTask(task: DailyTask) = dailyTasksDao.insertTask(task.toDailyTaskEntity())

    override suspend fun deleteTask(task: DailyTask) = dailyTasksDao.deleteTask(task.toDailyTaskEntity())

    override suspend fun deleteAllTasks() = dailyTasksDao.deleteAllTasks()
}