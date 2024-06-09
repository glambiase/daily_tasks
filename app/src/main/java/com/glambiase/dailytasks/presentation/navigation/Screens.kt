package com.glambiase.dailytasks.presentation.navigation

import androidx.navigation.NavHostController
import com.glambiase.dailytasks.presentation.util.Event

class Screens(navController: NavHostController) {

    val tasksList: (Event) -> Unit = { event ->
        navController.navigate(route = "tasks_list/${event.name}") {
            popUpTo(TASKS_LIST_SCREEN) { inclusive = true }
        }
    }

    val taskDetail: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task_detail/$taskId")
    }

    companion object {

        const val TASKS_LIST_SCREEN = "tasks_list/{event}"
        const val TASK_DETAIL_SCREEN = "task_detail/{taskId}"
    }
}