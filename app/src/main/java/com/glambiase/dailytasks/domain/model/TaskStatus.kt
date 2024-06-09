package com.glambiase.dailytasks.domain.model

import androidx.compose.ui.graphics.Color
import com.glambiase.dailytasks.ui.theme.TaskDoneColor
import com.glambiase.dailytasks.ui.theme.TaskInProgressColor
import com.glambiase.dailytasks.ui.theme.TaskToDoColor

enum class TaskStatus(val color: Color) {
    TO_DO(TaskToDoColor),
    IN_PROGRESS(TaskInProgressColor),
    DONE(TaskDoneColor),
    NONE(Color.Transparent)
}