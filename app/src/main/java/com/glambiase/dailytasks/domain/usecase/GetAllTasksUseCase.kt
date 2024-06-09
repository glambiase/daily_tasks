package com.glambiase.dailytasks.domain.usecase

import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.repository.DailyTasksRepository
import com.glambiase.dailytasks.domain.util.Sorting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val dailyTasksRepository: DailyTasksRepository
) {
    operator fun invoke(sorting: Sorting = Sorting.ByDate): Flow<List<DailyTask>> {
        return when (sorting) {
            Sorting.ByDate -> dailyTasksRepository.getAllTasks().map { tasks ->
                tasks.sortedByDescending { it.timestamp }
            }
            Sorting.ByDoneStatus -> dailyTasksRepository.sortTasksByDoneStatus()
            Sorting.ByToDoStatus -> dailyTasksRepository.sortTasksByToDoStatus()
        }
    }
}