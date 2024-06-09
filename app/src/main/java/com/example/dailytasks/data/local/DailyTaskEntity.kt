package com.example.dailytasks.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dailytasks.domain.model.TaskStatus

@Entity
data class DailyTaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val status: TaskStatus
)