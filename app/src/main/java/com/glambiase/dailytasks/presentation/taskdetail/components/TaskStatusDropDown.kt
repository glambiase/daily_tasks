package com.glambiase.dailytasks.presentation.taskdetail.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.glambiase.dailytasks.R
import com.glambiase.dailytasks.domain.model.TaskStatus
import com.glambiase.dailytasks.presentation.components.DailyTaskLabel

@Composable
fun TaskStatusDropDown(
    status: TaskStatus,
    onStatusSelected: (TaskStatus) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val rotationDegrees: Float by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "Angle Animation"
    )

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable { expanded = true }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.5f
                ),
                shape = MaterialTheme.shapes.extraSmall
            )
            .padding(16.dp)
    ) {
        DailyTaskLabel(
            text = status.getFormattedName(),
            color = status.color,
            textStyle = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { expanded = true },
            modifier = Modifier
                .alpha(0.5f)
                .rotate(degrees = rotationDegrees)
            ) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = stringResource(id = R.string.drop_down_cd)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(fraction = 0.75f)
        ) {
            TaskStatus.entries.toTypedArray().forEachIndexed { i, status ->
                DropdownMenuItem(
                    text = {
                        DailyTaskLabel(
                            text = status.getFormattedName(),
                            color = status.color
                        )
                    },
                    onClick = {
                        expanded = false
                        onStatusSelected(status)
                    }
                )
                if (i != TaskStatus.entries.lastIndex) {
                    Divider()
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun TaskStatusDropDownPreview() {
    TaskStatusDropDown(
        status = TaskStatus.DONE,
        onStatusSelected = {}
    )
}