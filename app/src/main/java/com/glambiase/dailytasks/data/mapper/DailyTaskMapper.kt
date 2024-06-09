package com.glambiase.dailytasks.data.mapper

import com.glambiase.dailytasks.data.local.DailyTaskEntity
import com.glambiase.dailytasks.domain.model.DailyTask

fun DailyTaskEntity.toDailyTask() =
    DailyTask(
        id = id,
        title = title,
        description = description,
        status = status
    )

fun DailyTask.toDailyTaskEntity() =
    DailyTaskEntity(
        id = id,
        title = title,
        description = description,
        status = status
    )