package com.glambiase.dailytasks.presentation.taskslist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.usecase.DeleteTaskUseCase
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
    private val insertTaskUseCase: InsertTaskUseCase
) : ViewModel() {

    private val _tasksState = mutableStateOf(TasksListState())
    val taskState: State<TasksListState> = _tasksState

    private var recentlyDeletedTask: DailyTask? = null

    private var getTasksJob: Job? = null

    init {
        getTasks(Sorting.ByDate)
    }

    private fun getTasks(sorting: Sorting) {
        getTasksJob?.cancel()
        getTasksJob = getAllTasksUseCase(sorting)
            .onEach { tasks ->
                _tasksState.value = taskState.value.copy(
                    tasks = tasks,
                    sorting = sorting
                )
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: TasksListEvent) {
        when (event) {
            is TasksListEvent.SortTasks -> {
                if (event.sorting != taskState.value.sorting) {
                    getTasks(event.sorting)
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
        }
    }
}