package com.glambiase.dailytasks.data.repository

import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.repository.DailyTasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDailyTasksRepository : DailyTasksRepository {
    override fun getAllTasks(): Flow<List<DailyTask>> =
        flow { emit(tasks) }

    override suspend fun getTaskById(taskId: Int): DailyTask =
        tasks.find { it.id == taskId } ?: tasks.first()

    override suspend fun insertTask(task: DailyTask) {
        tasks.add(task)
    }

    override suspend fun deleteTask(task: DailyTask) {
        tasks.remove(task)
    }

    override suspend fun deleteAllTasks() {
        tasks.clear()
    }

    private val tasks = mutableListOf<DailyTask>()
}