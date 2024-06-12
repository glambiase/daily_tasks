package com.glambiase.dailytasks.presentation.taskslist.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.glambiase.dailytasks.R
import com.glambiase.dailytasks.domain.util.Sorting
import com.glambiase.dailytasks.presentation.components.ConfirmationDialog
import com.glambiase.dailytasks.presentation.components.DailyTaskLabel
import com.glambiase.dailytasks.ui.theme.Purple80
import com.glambiase.dailytasks.ui.theme.TaskDoneColor
import com.glambiase.dailytasks.ui.theme.TaskToDoColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksListAppBar(
    onSortClick: (Sorting) -> Unit,
    onDeleteAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.tasks_app_bar),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        actions = {
            TasksListAppBarActions(
                onSortClick = onSortClick,
                onDeleteAllClick = onDeleteAllClick
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    )
}

@Composable
fun TasksListAppBarActions(
    onSortClick: (Sorting) -> Unit,
    onDeleteAllClick: () -> Unit
) {
    var showConfirmationDialog by remember { mutableStateOf(false) }
    ConfirmationDialog(
        title = R.string.delete_tasks_title,
        description = R.string.delete_tasks_description,
        showDialog = showConfirmationDialog,
        dismissDialog = { showConfirmationDialog = false },
        onConfirmClick = { onDeleteAllClick() }
    )

    SortAction(onSortClick = onSortClick)
    DeleteAllAction(onDeleteAllClick = { showConfirmationDialog = true })
}

@Composable
fun SortAction(
    onSortClick: (Sorting) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(
        onClick = { expanded = true },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.List,
            contentDescription = stringResource(id = R.string.sort_tasks_cd),
            tint = MaterialTheme.colorScheme.onPrimary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(fraction = 0.75f)
        ) {
            sortingItems.forEachIndexed { i, item ->
                DropdownMenuItem(
                    text = {
                        DailyTaskLabel(
                            text = stringResource(id = item.text),
                            color = item.color
                        )
                    },
                    onClick = {
                        expanded = false
                        onSortClick(item.sorting)
                    }
                )
                if (i != sortingItems.lastIndex) {
                    Divider()
                }
            }
        }
    }
}

@Composable
fun DeleteAllAction(
    onDeleteAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(
        onClick = { expanded = true },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(id = R.string.delete_tasks_cd),
            tint = MaterialTheme.colorScheme.onPrimary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(id = R.string.delete_tasks_title),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                onClick = {
                    expanded = false
                    onDeleteAllClick()
                }
            )
        }
    }
}

val sortingItems = listOf(
    SortingItem(
        text = R.string.by_date,
        color = Purple80,
        sorting = Sorting.ByDate
    ),
    SortingItem(
        text = R.string.by_done_status,
        color = TaskDoneColor,
        sorting = Sorting.ByDoneStatus
    ),
    SortingItem(
        text = R.string.by_to_do_status,
        color = TaskToDoColor,
        sorting = Sorting.ByToDoStatus
    )
)

data class SortingItem(
    val text: Int,
    val color: Color,
    val sorting: Sorting
)

@Composable
@Preview
private fun TasksListAppBarPreview() {
    TasksListAppBar(
        onSortClick = {},
        onDeleteAllClick = {}
    )
}