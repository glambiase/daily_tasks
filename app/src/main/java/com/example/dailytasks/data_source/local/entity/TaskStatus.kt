package com.example.dailytasks.data_source.local.entity

import androidx.compose.ui.graphics.Color
import com.example.dailytasks.ui.theme.TaskDoneColor
import com.example.dailytasks.ui.theme.TaskInProgressColor
import com.example.dailytasks.ui.theme.TaskToDoColor

enum class TaskStatus(color: Color) {
    TO_DO(TaskToDoColor),
    IN_PROGRESS(TaskInProgressColor),
    DONE(TaskDoneColor),
    NONE(Color.Transparent)
}