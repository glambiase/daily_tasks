package com.glambiase.dailytasks.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.glambiase.dailytasks.presentation.taskslist.TasksListScreen
import com.glambiase.dailytasks.ui.theme.DailyTasksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyTasksTheme {
                val navController = rememberNavController()
                TasksListScreen(navController = navController)
            }
        }
    }
}