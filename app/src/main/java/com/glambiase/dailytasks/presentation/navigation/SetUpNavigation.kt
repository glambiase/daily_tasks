package com.glambiase.dailytasks.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.glambiase.dailytasks.presentation.navigation.Screens.Companion.TASKS_LIST_SCREEN
import com.glambiase.dailytasks.presentation.viewmodel.DailyTasksViewModel

@Composable
fun SetUpNavigation(
    navController: NavHostController,
    dailyTasksViewModel: DailyTasksViewModel
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = TASKS_LIST_SCREEN,
    ) {

    }
}