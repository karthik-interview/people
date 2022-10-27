package com.zoho.core.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zoho.core.ui.theme.PeopleTheme

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {

    PeopleTheme {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}