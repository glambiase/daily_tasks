package com.glambiase.dailytasks.presentation.taskslist

import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.model.TaskStatus
import com.glambiase.dailytasks.domain.util.Sorting

sealed class TasksListEvent {

    data class SortTasks(val sorting: Sorting) : TasksListEvent()

    data class FilterTasks(val statusFilter: TaskStatus) : TasksListEvent()

    data class DeleteTask(val task: DailyTask) : TasksListEvent()

    data object RestoreTask : TasksListEvent()

    data object DeleteAllTasks : TasksListEvent()
}