package com.zoho.people.presentation.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.zoho.people.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    state: SavedStateHandle
) : ViewModel() {

    val userId = mutableStateOf("")

    init {
        userId.value = state[Destination.Detail.ArgId] ?: ""
    }
}