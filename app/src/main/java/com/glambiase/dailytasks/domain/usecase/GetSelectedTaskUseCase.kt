package com.glambiase.dailytasks.domain.usecase

import com.glambiase.dailytasks.domain.repository.DailyTasksRepository
import javax.inject.Inject

class GetSelectedTaskUseCase @Inject constructor(
    private val dailyTasksRepository: DailyTasksRepository
) {
    operator fun invoke(taskId: Int) = dailyTasksRepository.getSelectedTask(taskId)
}