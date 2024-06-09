package com.glambiase.dailytasks.presentation.taskslist

import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.util.Sorting

sealed class TasksListEvent {

    data class SortTasks(val sorting: Sorting) : TasksListEvent()

    data class DeleteTask(val task: DailyTask) : TasksListEvent()

    object RestoreTask : TasksListEvent()
}