package com.glambiase.dailytasks.domain.usecase

import com.glambiase.dailytasks.domain.repository.DailyTasksRepository
import javax.inject.Inject

class DeleteAllTasksUseCase @Inject constructor(
    private val dailyTasksRepository: DailyTasksRepository
) {
    suspend operator fun invoke() = dailyTasksRepository.deleteAllTasks()
}