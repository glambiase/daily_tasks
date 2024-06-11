package com.glambiase.dailytasks.domain.usecase

import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.model.TaskStatus
import com.glambiase.dailytasks.domain.repository.DailyTasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilterTasksByStatusUseCase @Inject constructor(
    private val dailyTasksRepository: DailyTasksRepository
) {
    operator fun invoke(taskStatus: TaskStatus): Flow<List<DailyTask>> =
        dailyTasksRepository.getAllTasks().map { tasks ->
            tasks.filter { it.status == taskStatus }
        }
}