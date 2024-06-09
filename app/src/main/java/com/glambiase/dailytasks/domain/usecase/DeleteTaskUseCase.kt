package com.glambiase.dailytasks.domain.usecase

import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.repository.DailyTasksRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val dailyTasksRepository: DailyTasksRepository
) {
    suspend operator fun invoke(dailyTask: DailyTask) = dailyTasksRepository.deleteTask(dailyTask)
}