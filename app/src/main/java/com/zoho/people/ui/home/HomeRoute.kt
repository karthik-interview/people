package com.zoho.people.ui.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.zoho.people.navigation.navigateToDetail
import com.zoho.people.presentation.home.HomeViewModel

@Composable
fun HomeRoute(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val pagingItems = homeViewModel.usersPaging.collectAsLazyPagingItems()

    HomeScreen(
        pagingItems = pagingItems,
        onClickUser = { navController.navigateToDetail(it.email) },
    )
}