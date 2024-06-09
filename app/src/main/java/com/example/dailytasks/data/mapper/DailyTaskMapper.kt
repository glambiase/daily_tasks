package com.example.dailytasks.data.mapper

import com.example.dailytasks.data.local.DailyTaskEntity
import com.example.dailytasks.domain.model.DailyTask

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