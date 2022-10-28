package com.zoho.people.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pagingSource: UserPagingSource,
) : ViewModel() {

    val usersPaging = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        pagingSource
    }.flow
        .cachedIn(viewModelScope)
}
