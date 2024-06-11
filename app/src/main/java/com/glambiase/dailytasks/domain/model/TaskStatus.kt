package com.glambiase.dailytasks.domain.model

import androidx.compose.ui.graphics.Color
import com.glambiase.dailytasks.ui.theme.TaskDoneColor
import com.glambiase.dailytasks.ui.theme.TaskInProgressColor
import com.glambiase.dailytasks.ui.theme.TaskToDoColor
import java.util.Locale

enum class TaskStatus(val color: Color) {
    COMPLETATA(TaskDoneColor),
    IN_CORSO(TaskInProgressColor),
    DA_FARE(TaskToDoColor);

    fun getFormattedName() =
        name.replace("_", " ")
            .lowercase(Locale.ROOT)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
}