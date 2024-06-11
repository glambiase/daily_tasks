package com.glambiase.dailytasks.presentation.taskdetail.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.glambiase.dailytasks.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailAppBar(
    onBackClick: () -> Unit,
    onInsertClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClick = onBackClick)
        },
        title = {
            Text(
                text = stringResource(id = R.string.task_detail_app_bar),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        actions = {
            InsertAction(onInsertClick = onInsertClick)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    )
}

@Composable
fun BackAction(
    onBackClick: () -> Unit
) {
    IconButton(
        onClick = { onBackClick() }
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.go_back_cd),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun InsertAction(
    onInsertClick: () -> Unit
) {
    IconButton(
        onClick = { onInsertClick() }
    ) {
        Icon(
            imageVector = Icons.Filled.CheckCircle, 
            contentDescription = stringResource(id = R.string.add_task_cd),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
@Preview
private fun TaskDetailAppBarPreview() {
    TaskDetailAppBar(
        onBackClick = {},
        onInsertClick = {}
    )
}