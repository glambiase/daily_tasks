package com.example.dailytasks.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailytasks.data_source.local.entity.DailyTask
import com.example.dailytasks.data_source.repository.DailyTasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyTasksViewModel @Inject constructor(
    private val dailyTasksRepository: DailyTasksRepository
) : ViewModel() {

    private val _allTasks = MutableStateFlow<List<DailyTask>>(emptyList())
    var allTasks = _allTasks.asStateFlow()

    fun getAllTasks() {
        viewModelScope.launch {
            dailyTasksRepository.getAllTasks().collect {
                _allTasks.value = it
            }
        }
    }
}