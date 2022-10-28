package com.zoho.people.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoho.people.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _state: MutableState<HomeUiState> = mutableStateOf(HomeUiState.Loading)
    val state: State<HomeUiState> = _state

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            try {
                val users = userRepository.getUsers(1)
                _state.value = HomeUiState.Listing(users)
            } catch (e: Exception) {
                _state.value = HomeUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
