package com.example.dailytasks.domain.usecase

import com.example.dailytasks.domain.repository.DailyTasksRepository
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val dailyTasksRepository: DailyTasksRepository
) {
    operator fun invoke() = dailyTasksRepository.getAllTasks()
}