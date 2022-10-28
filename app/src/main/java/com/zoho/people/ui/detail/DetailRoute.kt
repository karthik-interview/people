package com.zoho.people.ui.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zoho.core.ui.component.Screen

@Composable
fun DetailRoute() {

    Screen(modifier = Modifier.fillMaxSize()) {
        Text(text = "Details screen")
    }
}