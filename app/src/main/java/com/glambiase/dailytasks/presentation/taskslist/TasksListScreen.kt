package com.glambiase.dailytasks.presentation.taskslist

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.glambiase.dailytasks.R
import com.glambiase.dailytasks.presentation.components.Fab
import com.glambiase.dailytasks.presentation.taskslist.components.TasksListAppBar
import com.glambiase.dailytasks.presentation.taskslist.components.TasksListContent
import kotlinx.coroutines.launch

@Composable
fun TasksListScreen(
    navController: NavController,
    viewModel: TasksListViewModel = hiltViewModel()
) {
    val state = viewModel.tasksState

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val snackbarMessage = stringResource(id = R.string.snackbar_deleted_task_msg)
    val snackbarActionLabel = stringResource(id = R.string.snackbar_undo)

    Scaffold(
        topBar = {
            TasksListAppBar(
                onSortClick = {
                    viewModel.onEvent(TasksListEvent.SortTasks(it))
                },
                onDeleteAllClick = {
                    viewModel.onEvent(TasksListEvent.DeleteAllTasks)
                }
            )
        },
        content = { paddingValues ->
            TasksListContent(
                statusFilter = state.statusFilter,
                onFilterSelected = {
                    viewModel.onEvent(TasksListEvent.FilterTasks(it))
                },
                tasks = state.tasks,
                onTaskClick = {}, // navController.navigate
                onDeleteTaskClick = {
                    viewModel.onEvent(TasksListEvent.DeleteTask(it))
                    scope.launch {
                        val snackbarResult = snackbarHostState.showSnackbar(
                            message = snackbarMessage,
                            actionLabel = snackbarActionLabel,
                            duration = SnackbarDuration.Short
                        )
                        if (snackbarResult == SnackbarResult.ActionPerformed) {
                            viewModel.onEvent(TasksListEvent.RestoreTask)
                        }
                    }
                },
                modifier = Modifier.padding(
                    start = paddingValues.calculateStartPadding(layoutDirection = LayoutDirection.Ltr),
                    top = paddingValues.calculateTopPadding(),
                    end = paddingValues.calculateEndPadding(layoutDirection = LayoutDirection.Ltr),
                    bottom = paddingValues.calculateBottomPadding()
                )
            )
        },
        floatingActionButton = {
            Fab(
                image = Icons.Filled.Add,
                contentDesc = R.string.fab_cd,
                onClick = { } //navController.navigate()
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    )
}