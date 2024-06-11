package com.glambiase.dailytasks.presentation.taskslist

import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.model.TaskStatus
import com.glambiase.dailytasks.domain.util.Sorting

data class TasksListState(
    val tasks: List<DailyTask> = emptyList(),
    val sorting: Sorting = Sorting.ByDate,
    val statusFilter: TaskStatus? = null
)