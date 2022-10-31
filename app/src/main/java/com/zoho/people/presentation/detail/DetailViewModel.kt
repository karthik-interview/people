package com.zoho.people.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoho.people.presentation.detail.DetailUiState.UserDetailFoundUi
import com.zoho.people.presentation.detail.DetailUiState.UserNotFound
import com.zoho.people.ui.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.thedukerchip.domain.usecase.GetUserByIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    state: SavedStateHandle,
    private val getUserByIdUseCase: GetUserByIdUseCase,
) : ViewModel() {

    private val _state = mutableStateOf<DetailUiState>(DetailUiState.Loading)
    val state: State<DetailUiState> get() = _state

    private val _userId = state[Destination.Detail.ArgId] ?: ""

    init {
        getUserDetails()
    }

    private fun getUserDetails() {
        if (_userId.isEmpty()) {
            _state.value = UserNotFound
            return
        }

        viewModelScope.launch {
            val user = getUserByIdUseCase(_userId)
            _state.value = if (user != null) UserDetailFoundUi(user) else UserNotFound
        }
    }
}

