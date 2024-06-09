package com.glambiase.dailytasks.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.glambiase.dailytasks.domain.model.TaskStatus

@Entity
data class DailyTaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val status: TaskStatus,
    val timestamp: Long
)