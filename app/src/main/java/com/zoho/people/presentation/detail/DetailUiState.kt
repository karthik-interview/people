package com.zoho.people.presentation.detail

import com.zoho.people.core.model.data.User

sealed class DetailUiState {
    object Loading : DetailUiState()
    object UserNotFound : DetailUiState()
    class UserDetailFoundUi(val user: User) : DetailUiState()
}