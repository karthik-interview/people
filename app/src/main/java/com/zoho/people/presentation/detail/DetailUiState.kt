package com.zoho.people.presentation.detail

import dev.thedukerchip.domain.models.UserEntity

sealed class DetailUiState {
    object Loading : DetailUiState()
    object UserNotFound : DetailUiState()
    class UserDetailFoundUi(val userEntity: UserEntity) : DetailUiState()
}