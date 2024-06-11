package com.glambiase.dailytasks.presentation.taskslist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.glambiase.dailytasks.R
import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.model.TaskStatus

@Composable
fun TasksListContent(
    statusFilter: TaskStatus?,
    onFilterSelected: (TaskStatus) -> Unit,
    tasks: List<DailyTask>,
    onTaskClick: () -> Unit,
    onDeleteTaskClick: (DailyTask) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        TasksListFilteringRow(
            selectedFilter = statusFilter,
            onFilterSelected = onFilterSelected
        )
        Spacer(modifier = Modifier.height(24.dp))
        TasksList(
            tasks = tasks,
            onTaskClick = onTaskClick,
            onDeleteTaskClick = onDeleteTaskClick
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun TasksListContentPreview() {
    TasksListContent(
        statusFilter = TaskStatus.COMPLETATA,
        onFilterSelected = {},
        tasks = listOf(
            DailyTask(
                id = 0,
                title = stringResource(id = R.string.app_name),
                description = stringResource(id = R.string.app_name),
                status = TaskStatus.COMPLETATA,
                timestamp = 0L
            ),
            DailyTask(
                id = 0,
                title = stringResource(id = R.string.app_name),
                description = stringResource(id = R.string.app_name),
                status = TaskStatus.IN_CORSO,
                timestamp = 0L
            ),
            DailyTask(
                id = 0,
                title = stringResource(id = R.string.app_name),
                description = stringResource(id = R.string.app_name),
                status = TaskStatus.DA_FARE,
                timestamp = 0L
            )
        ),
        onTaskClick = {},
        onDeleteTaskClick = {}
    )
}