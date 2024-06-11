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
import com.glambiase.dailytasks.domain.usecase.FilterTasksByStatusUseCase
import com.glambiase.dailytasks.domain.usecase.GetAllTasksUseCase
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
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val insertTaskUseCase: InsertTaskUseCase,
    private val deleteAllTasksUseCase: DeleteAllTasksUseCase,
    private val filterTasksByStatusUseCase: FilterTasksByStatusUseCase
) : ViewModel() {

    /*
    private val _tasksState = mutableStateOf(TasksListState())
    val tasksState: State<TasksListState> = _tasksState
    */

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
                    getTasks(event.sorting)
                }
            }
            is TasksListEvent.FilterTasks -> {
                if (event.statusFilter == tasksState.statusFilter) {
                    getTasks()
                    tasksState = tasksState.copy(
                        statusFilter = null
                    )
                } else {
                    filterTasks(event.statusFilter)
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
                    deleteAllTasksUseCase
                }
            }
        }
    }

    private fun getTasks(sorting: Sorting = Sorting.ByDate) {
        tasksJob?.cancel()
        tasksJob = getAllTasksUseCase(sorting)
            .onEach { tasks ->
                tasksState = tasksState.copy(
                    tasks = tasks,
                    sorting = sorting
                )
            }
            .launchIn(viewModelScope)
    }

    private fun filterTasks(statusFilter: TaskStatus) {
        tasksJob?.cancel()
        tasksJob = filterTasksByStatusUseCase(statusFilter)
            .onEach { tasks ->
                tasksState = tasksState.copy(
                    tasks = tasks,
                    statusFilter = statusFilter
                )
            }
            .launchIn(viewModelScope)
    }
}