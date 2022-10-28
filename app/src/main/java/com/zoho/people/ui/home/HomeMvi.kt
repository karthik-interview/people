package com.zoho.people.ui.home

import com.zoho.people.models.presentation.UserEntity

sealed class HomeUiState {
    class Listing(val users: List<UserEntity>): HomeUiState()
    object Loading: HomeUiState()
    class Error(val message: String): HomeUiState()
}