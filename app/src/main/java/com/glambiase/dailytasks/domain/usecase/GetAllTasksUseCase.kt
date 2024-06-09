package com.glambiase.dailytasks.domain.usecase

import com.glambiase.dailytasks.domain.repository.DailyTasksRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val dailyTasksRepository: DailyTasksRepository
) {
    operator fun invoke() = dailyTasksRepository.getAllTasks().map { tasks ->
        tasks.sortedByDescending { it.timestamp }
    }
}