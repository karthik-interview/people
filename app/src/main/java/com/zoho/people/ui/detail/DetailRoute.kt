package com.zoho.people.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.zoho.core.ui.component.Screen
import com.zoho.people.presentation.detail.DetailViewModel

@Composable
fun DetailRoute(
    detailVm: DetailViewModel = hiltViewModel()
) {
    Screen(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(text = detailVm.userId.value)
        }
    }
}