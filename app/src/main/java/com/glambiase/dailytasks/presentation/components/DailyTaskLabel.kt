package com.glambiase.dailytasks.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.glambiase.dailytasks.R
import com.glambiase.dailytasks.ui.theme.TaskDoneColor

@Composable
fun DailyTaskLabel(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Canvas(
            modifier = Modifier.size(16.dp)
        ) {
            drawCircle(color = color)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = textStyle,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun DailyTaskLabelPreview() {
    DailyTaskLabel(
        text = stringResource(id = R.string.done),
        color = TaskDoneColor,
    )
}