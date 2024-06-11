package com.glambiase.dailytasks.presentation.taskslist.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.glambiase.dailytasks.R
import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.model.TaskStatus

@Composable
fun TasksList(
    tasks: List<DailyTask>,
    onTaskClick: () -> Unit,
    onDeleteTaskClick: (DailyTask) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(tasks) {
            DailyTaskItem(
                dailyTask = it,
                onClick = onTaskClick,
                onDeleteClick = { onDeleteTaskClick(it) }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun TasksListPreview() {
    TasksList(
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