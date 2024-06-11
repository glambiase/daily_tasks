package com.glambiase.dailytasks.presentation.taskslist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.glambiase.dailytasks.domain.model.TaskStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksListFilteringRow(
    selectedFilter: TaskStatus?,
    onFilterSelected: (TaskStatus) -> Unit,
    modifier: Modifier = Modifier
) {
    val statuses = TaskStatus.entries

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(statuses) { status ->
            val isSelected = status == selectedFilter
            FilterChip(
                selected = isSelected,
                onClick = { onFilterSelected(status) },
                label = {
                    Text(
                        text = status.getFormattedName(),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,

                        )
                },
                leadingIcon = if (isSelected) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null
                        )
                    }
                } else null
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun TasksListFilteringRowPreview() {
    TasksListFilteringRow(
        selectedFilter = TaskStatus.IN_CORSO,
        onFilterSelected = {}
    )
}