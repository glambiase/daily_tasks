package com.glambiase.dailytasks.presentation.components

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.glambiase.dailytasks.R

@Composable
fun ConfirmationDialog(
    @StringRes title: Int,
    @StringRes description: Int,
    showDialog: Boolean,
    dismissDialog: () -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (showDialog) {
        AlertDialog(
            title = {
                Text(
                    text = stringResource(id = title),
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                )
            },
            text = {
                Text(
                    text = stringResource(id = description),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Normal
                )
            },
            onDismissRequest = { dismissDialog() },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirmClick()
                        dismissDialog()
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.yes),
                    )
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        dismissDialog()
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.no),
                    )
                }
            },
            modifier = modifier
        )
    }
}

@Composable
@Preview
private fun ConfirmationDialogPreview() {
    ConfirmationDialog(
        title = R.string.app_name,
        description = R.string.app_name,
        showDialog = true,
        dismissDialog = {},
        onConfirmClick = {}
    )
}