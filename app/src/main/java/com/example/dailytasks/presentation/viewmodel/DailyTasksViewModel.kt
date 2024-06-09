package com.example.dailytasks.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailytasks.domain.model.DailyTask
import com.example.dailytasks.domain.usecase.GetAllTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyTasksViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase
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
}