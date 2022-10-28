package com.zoho.people.ui.detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zoho.people.presentation.detail.DetailViewModel

@Composable
fun DetailRoute(
    detailVm: DetailViewModel = hiltViewModel(),
    navController: NavHostController
) {
    DetailScreen(
        state = detailVm.state.value,
        onClickBack = { navController.popBackStack() },
        modifier = Modifier.fillMaxWidth()
    )
}