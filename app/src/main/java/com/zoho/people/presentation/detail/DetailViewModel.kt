package com.zoho.people.presentation.detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoho.people.data.UserRepository
import com.zoho.people.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    state: SavedStateHandle,
    private val repository: UserRepository,
) : ViewModel() {

    val userId = mutableStateOf("")
    private val _userId = state[Destination.Detail.ArgId] ?: ""

    init {
        userId.value = state[Destination.Detail.ArgId] ?: ""
        getUserDetails()
    }

    private fun getUserDetails() {
        if (_userId.isEmpty()) return // TODO Handle the empty user id use case

        viewModelScope.launch {
            val user = repository.getUserById(_userId)
            Log.i("DetailScreen", user.toString())
        }
    }
}