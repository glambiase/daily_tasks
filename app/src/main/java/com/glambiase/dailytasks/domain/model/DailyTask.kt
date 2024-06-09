package com.glambiase.dailytasks.domain.model

data class DailyTask(
    val id: Int,
    val title: String,
    val description: String,
    val status: TaskStatus,
    val timestamp: Long
)