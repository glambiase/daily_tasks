package com.glambiase.dailytasks.presentation.taskdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.glambiase.dailytasks.R
import com.glambiase.dailytasks.domain.model.TaskStatus

@Composable
fun TaskDetailContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    status: TaskStatus,
    onStatusSelected: (TaskStatus) -> Unit,
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
        OutlinedTextField(
            value = title,
            onValueChange = { onTitleChange(it) },
            label = {
                Text(
                    text = stringResource(id = R.string.task_detail_title)
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TaskStatusDropDown(
            status = status,
            onStatusSelected = { onStatusSelected(it) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { onDescriptionChange(it) },
            label = {
                Text(
                    text = stringResource(id = R.string.task_detail_description)
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
@Preview
private fun TaskDetailContentPreview() {
    TaskDetailContent(
        title = "",
        onTitleChange = {},
        description = "",
        onDescriptionChange = {},
        status = TaskStatus.DONE,
        onStatusSelected = {}
    )
}