package com.glambiase.dailytasks.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.usecase.GetAllTasksUseCase
import com.glambiase.dailytasks.domain.usecase.GetSelectedTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyTasksViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val getSelectedTaskUseCase: GetSelectedTaskUseCase
) : ViewModel() {

    private val _allTasks = MutableStateFlow<List<DailyTask>>(emptyList())
    var allTasks = _allTasks.asStateFlow()

    fun getAllTasks() {
        viewModelScope.launch {
            getAllTasksUseCase().collect {
                _allTasks.value = it
            }
        }
    }

    private val _selectedTask = MutableStateFlow<DailyTask?>(null)
    val selectedTask = _selectedTask.asStateFlow()

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            getSelectedTaskUseCase(taskId).collect {
                _selectedTask.value = it
            }
        }
    }
}