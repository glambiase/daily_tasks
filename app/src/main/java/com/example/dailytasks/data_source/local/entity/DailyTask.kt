package com.example.dailytasks.data_source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DailyTask(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val status: TaskStatus
)