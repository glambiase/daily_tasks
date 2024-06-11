package com.glambiase.dailytasks.presentation.taskdetail

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.glambiase.dailytasks.presentation.taskdetail.components.TaskDetailAppBar
import com.glambiase.dailytasks.presentation.taskdetail.components.TaskDetailContent

@Composable
fun TaskDetailScreen(
    navController: NavController,
    viewModel: TaskDetailViewModel = hiltViewModel()
) {
    val titleState = viewModel.title
    val descriptionState = viewModel.description
    val statusState = viewModel.status

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.oneTimeEvent.collect { event ->
            when (event) {
                TaskDetailViewModel.OneTimeEvent.TaskSaved -> {
                    navController.navigateUp()
                }
                is TaskDetailViewModel.OneTimeEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TaskDetailAppBar(
                onBackClick = { navController.navigateUp() },
                onInsertClick = { viewModel.onEvent(TaskDetailEvent.SaveTask) }
            )
        },
        content = { paddingValues ->
            TaskDetailContent(
                title = titleState,
                onTitleChange = { viewModel.onEvent(TaskDetailEvent.ChangeTitle(it)) },
                description = descriptionState,
                onDescriptionChange = { viewModel.onEvent(TaskDetailEvent.ChangeDescription(it)) },
                status = statusState,
                onStatusSelected = { viewModel.onEvent(TaskDetailEvent.ChangeStatus(it)) },
                modifier = Modifier.padding(
                    start = paddingValues.calculateStartPadding(layoutDirection = LayoutDirection.Ltr),
                    top = paddingValues.calculateTopPadding(),
                    end = paddingValues.calculateEndPadding(layoutDirection = LayoutDirection.Ltr),
                    bottom = paddingValues.calculateBottomPadding()
                )
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    )
}