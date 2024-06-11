package com.glambiase.dailytasks.presentation.taskdetail

import com.glambiase.dailytasks.domain.model.TaskStatus

sealed class TaskDetailEvent {

    data class ChangeTitle(val title: String) : TaskDetailEvent()

    data class ChangeDescription(val description: String) : TaskDetailEvent()

    data class ChangeStatus(val status: TaskStatus) : TaskDetailEvent()

    data object SaveTask : TaskDetailEvent()
}