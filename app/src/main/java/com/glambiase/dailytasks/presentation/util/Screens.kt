package com.glambiase.dailytasks.presentation.util

sealed class Screens(val route: String) {

    data object TasksListScreen : Screens("tasks_list_screen")

    data object TaskDetailScreen : Screens("task_detail_screen")
}