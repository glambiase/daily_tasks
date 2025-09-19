package com.glambiase.dailytasks.presentation.taskdetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.model.TaskStatus
import com.glambiase.dailytasks.domain.usecase.GetSelectedTaskUseCase
import com.glambiase.dailytasks.domain.usecase.InsertTaskUseCase
import com.glambiase.dailytasks.presentation.util.Constants.TASK_ID_ARG_NAME
import com.glambiase.dailytasks.presentation.util.Constants.TASK_ID_ARG_DEFAULT_VALUE
import com.glambiase.dailytasks.domain.util.EmptyTaskException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val getSelectedTaskUseCase: GetSelectedTaskUseCase,
    private val insertTaskUseCase: InsertTaskUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var status by mutableStateOf(TaskStatus.TO_DO)
        private set

    private val _oneTimeEvent = Channel<OneTimeEvent>()
    val oneTimeEvent = _oneTimeEvent.receiveAsFlow()

    private var taskId: Int? = null
    private var timestamp: Long? = null

    init {
        savedStateHandle.get<Int>(TASK_ID_ARG_NAME)?.let { id ->
            if (id != TASK_ID_ARG_DEFAULT_VALUE) {
                viewModelScope.launch {
                    getSelectedTaskUseCase(id)?.also { task ->
                        taskId = task.id
                        title = task.title
                        description = task.description
                        status = task.status
                        timestamp = task.timestamp
                    }
                }
            }
        }
    }

    fun onEvent(event: TaskDetailEvent) {
        when (event) {
            is TaskDetailEvent.ChangeTitle -> {
                title = event.title
            }
            is TaskDetailEvent.ChangeDescription -> {
                description = event.description
            }
            is TaskDetailEvent.ChangeStatus -> {
                status = event.status
            }
            TaskDetailEvent.SaveTask -> {
                viewModelScope.launch {
                    try {
                        insertTaskUseCase(
                            DailyTask(
                                id = taskId,
                                title = title,
                                description = description,
                                status = status,
                                timestamp = timestamp ?: System.currentTimeMillis()
                            )
                        )
                        _oneTimeEvent.send(OneTimeEvent.TaskSaved)
                    } catch (e: EmptyTaskException) {
                        _oneTimeEvent.send(OneTimeEvent.ShowSnackbar(message = e.message ?: "Invalid task entered."))
                    }
                }
            }
        }
    }

    sealed class OneTimeEvent {
        data class ShowSnackbar(val message: String) : OneTimeEvent()
        data object TaskSaved : OneTimeEvent()
    }
}