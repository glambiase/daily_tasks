package com.glambiase.dailytasks.domain.usecase

import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.model.TaskStatus
import com.glambiase.dailytasks.domain.repository.DailyTasksRepository
import com.glambiase.dailytasks.domain.util.Sorting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val dailyTasksRepository: DailyTasksRepository
) {
    operator fun invoke(sorting: Sorting, statusFilter: TaskStatus?): Flow<List<DailyTask>> =
        statusFilter?.let { status ->
            getSortedTasks(dailyTasksRepository, sorting).map { tasks ->
                tasks.filter { it.status == status }
            }
        } ?: run {
            getSortedTasks(dailyTasksRepository, sorting)
        }
}

private fun getSortedTasks(dailyTasksRepository: DailyTasksRepository, sorting: Sorting) =
    when (sorting) {
        Sorting.ByDate-> {
            dailyTasksRepository.getAllTasks().map { tasks ->
                tasks.sortedByDescending { it.timestamp }
            }
        }
        Sorting.ByDoneStatus -> {
            dailyTasksRepository.getAllTasks().map { tasks ->
                tasks.sortedBy { it.status.ordinal}
            }
        }
        Sorting.ByToDoStatus -> {
            dailyTasksRepository.getAllTasks().map { tasks ->
                tasks.sortedByDescending { it.status.ordinal}
            }
        }
    }