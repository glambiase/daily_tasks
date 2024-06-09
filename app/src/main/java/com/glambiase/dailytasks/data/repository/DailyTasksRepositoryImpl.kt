package com.glambiase.dailytasks.data.repository

import com.glambiase.dailytasks.data.local.DailyTasksDao
import com.glambiase.dailytasks.data.mapper.toDailyTask
import com.glambiase.dailytasks.data.mapper.toDailyTaskEntity
import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.repository.DailyTasksRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.map

@ViewModelScoped
class DailyTasksRepositoryImpl(
    private val dailyTasksDao: DailyTasksDao
) : DailyTasksRepository {

    override fun getAllTasks() = dailyTasksDao.getAllTasks().map { dailyTaskEntities ->
        dailyTaskEntities.map {
            it.toDailyTask()
        }
    }

    override fun getSelectedTask(taskId: Int) = dailyTasksDao.getSelectedTask(taskId).map {
        it.toDailyTask()
    }

    override suspend fun addTask(task: DailyTask) = dailyTasksDao.addTask(task.toDailyTaskEntity())

    override suspend fun updateTask(task: DailyTask) = dailyTasksDao.updateTask(task.toDailyTaskEntity())

    override suspend fun deleteTask(task: DailyTask) = dailyTasksDao.deleteTask(task.toDailyTaskEntity())

    override suspend fun deleteAllTasks() = dailyTasksDao.deleteAllTasks()

    override fun searchDB(query: String) = dailyTasksDao.searchDB(query).map { dailyTaskEntities ->
        dailyTaskEntities.map {
            it.toDailyTask()
        }
    }

    override fun sortTasksByDoneStatus() = dailyTasksDao.sortTasksByDoneStatus().map { dailyTaskEntities ->
        dailyTaskEntities.map {
            it.toDailyTask()
        }
    }

    override fun sortTasksByToDoStatus() = dailyTasksDao.sortTasksByToDoStatus().map { dailyTaskEntities ->
        dailyTaskEntities.map {
            it.toDailyTask()
        }
    }
}