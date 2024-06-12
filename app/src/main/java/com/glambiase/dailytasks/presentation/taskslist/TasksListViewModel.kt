package com.glambiase.dailytasks.presentation.taskslist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.model.TaskStatus
import com.glambiase.dailytasks.domain.usecase.DeleteAllTasksUseCase
import com.glambiase.dailytasks.domain.usecase.DeleteTaskUseCase
import com.glambiase.dailytasks.domain.usecase.GetTasksUseCase
import com.glambiase.dailytasks.domain.usecase.InsertTaskUseCase
import com.glambiase.dailytasks.domain.util.Sorting
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksListViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val insertTaskUseCase: InsertTaskUseCase,
    private val deleteAllTasksUseCase: DeleteAllTasksUseCase
) : ViewModel() {

    var tasksState by mutableStateOf(TasksListState())
        private set

    private var recentlyDeletedTask: DailyTask? = null

    private var tasksJob: Job? = null

    init {
        getTasks()
    }

    fun onEvent(event: TasksListEvent) {
        when (event) {
            is TasksListEvent.SortTasks -> {
                if (event.sorting != tasksState.sorting) {
                    getTasks(event.sorting, tasksState.statusFilter)
                }
            }
            is TasksListEvent.FilterTasks -> {
                if (event.statusFilter == tasksState.statusFilter) {
                    getTasks(tasksState.sorting, null)
                } else {
                    getTasks(tasksState.sorting, event.statusFilter)
                }
            }
            is TasksListEvent.DeleteTask -> {
                viewModelScope.launch {
                    deleteTaskUseCase(event.task)
                    recentlyDeletedTask = event.task
                }
            }
            TasksListEvent.RestoreTask -> {
                viewModelScope.launch {
                    insertTaskUseCase(recentlyDeletedTask ?: return@launch)
                    recentlyDeletedTask = null
                }
            }
            TasksListEvent.DeleteAllTasks -> {
                viewModelScope.launch {
                    deleteAllTasksUseCase()
                }
            }
        }
    }

    private fun getTasks(
        sorting: Sorting = Sorting.ByDate,
        statusFilter: TaskStatus? = null
    ) {
        tasksJob?.cancel()
        tasksJob = getTasksUseCase(sorting, statusFilter)
            .onEach { tasks ->
                tasksState = tasksState.copy(
                    tasks = tasks,
                    sorting = sorting,
                    statusFilter = statusFilter
                )
            }
            .launchIn(viewModelScope)
    }
}