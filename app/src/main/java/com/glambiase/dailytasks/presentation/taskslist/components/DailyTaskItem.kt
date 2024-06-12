package com.glambiase.dailytasks.presentation.taskslist.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.glambiase.dailytasks.R
import com.glambiase.dailytasks.domain.model.DailyTask
import com.glambiase.dailytasks.domain.model.TaskStatus

@Composable
fun DailyTaskItem(
    dailyTask: DailyTask,
    onClick: (Int?) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 4.dp,
        onClick = { onClick(dailyTask.id) },
        border = BorderStroke(
            width = 2.dp,
            color = dailyTask.status.color
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = dailyTask.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = dailyTask.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 8,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = stringResource(id = R.string.delete_task_cd),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
@Preview
private fun DailyTaskItemPreview() {
    DailyTaskItem(
        dailyTask = DailyTask(
            id = 0,
            title = stringResource(id = R.string.app_name),
            description = stringResource(id = R.string.app_name),
            status = TaskStatus.COMPLETATA,
            timestamp = 0L
        ),
        onClick = {},
        onDeleteClick = {}
    )
}