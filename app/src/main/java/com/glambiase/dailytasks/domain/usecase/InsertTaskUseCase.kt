package com.glambiase.dailytasks.domain.usecase

import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.repository.DailyTasksRepository
import com.glambiase.dailytasks.presentation.util.EmptyTaskException
import javax.inject.Inject

class InsertTaskUseCase @Inject constructor(
    private val dailyTasksRepository: DailyTasksRepository
) {
    suspend operator fun invoke(dailyTask: DailyTask) {
        if (dailyTask.title.isBlank() && dailyTask.description.isBlank()) {
            throw EmptyTaskException("Empty task discarded.")
        }
        dailyTasksRepository.insertTask(dailyTask)
    }
}