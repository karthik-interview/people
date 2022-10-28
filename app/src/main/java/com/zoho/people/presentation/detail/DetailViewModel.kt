package com.zoho.people.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoho.people.data.UserRepository
import com.zoho.people.models.presentation.UserEntity
import com.zoho.people.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    state: SavedStateHandle,
    private val repository: UserRepository,
) : ViewModel() {

    private val _state = mutableStateOf<DetailState>(DetailState.Loading)
    val state: State<DetailState> get() = _state

    private val _userId = state[Destination.Detail.ArgId] ?: ""

    init {
        getUserDetails()
    }

    private fun getUserDetails() {
        if (_userId.isEmpty()) return // TODO Handle the empty user id use case

        viewModelScope.launch {
            val user = repository.getUserById(_userId) // TODO Handle user not found case
            _state.value = DetailState.UserDetailFound(user)
        }
    }
}

sealed class DetailState {
    object Loading : DetailState()
    class UserDetailFound(val userEntity: UserEntity) : DetailState()
}