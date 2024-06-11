package com.glambiase.dailytasks.presentation.components

import androidx.annotation.StringRes
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource

@Composable
fun Fab(
    image: ImageVector,
    @StringRes contentDesc: Int,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = image,
            contentDescription = stringResource(contentDesc))
    }
}