package com.glambiase.dailytasks.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.glambiase.dailytasks.presentation.taskdetail.TaskDetailScreen
import com.glambiase.dailytasks.presentation.taskslist.TasksListScreen
import com.glambiase.dailytasks.presentation.util.Constants.TASK_ID_ARG_NAME
import com.glambiase.dailytasks.presentation.util.Constants.TASK_ID_ARG_DEFAULT_VALUE
import com.glambiase.dailytasks.presentation.util.Screens
import com.glambiase.dailytasks.ui.theme.DailyTasksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyTasksTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screens.TasksListScreen.route
                ) {
                    composable(
                        route = Screens.TasksListScreen.route
                    ) {
                        TasksListScreen(navController = navController)
                    }
                    composable(
                        route = "${Screens.TaskDetailScreen.route}?${TASK_ID_ARG_NAME}={${TASK_ID_ARG_NAME}}",
                        arguments = listOf(
                            navArgument(name = TASK_ID_ARG_NAME) {
                                type = NavType.IntType
                                defaultValue = TASK_ID_ARG_DEFAULT_VALUE
                            }
                        )
                    ) {
                        TaskDetailScreen(navController = navController)
                    }
                }
            }
        }
    }
}