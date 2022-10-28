package com.zoho.people

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.paging.compose.collectAsLazyPagingItems
import com.zoho.people.presentation.home.HomeViewModel
import com.zoho.people.ui.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val paging = viewModel.usersPaging.collectAsLazyPagingItems()

            HomeScreen(paging)
        }
    }
}