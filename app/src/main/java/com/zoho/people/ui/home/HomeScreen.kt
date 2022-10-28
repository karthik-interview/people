package com.zoho.people.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.zoho.core.ui.component.LazyColumnListing
import com.zoho.core.ui.component.Screen
import com.zoho.people.models.presentation.UserEntity

@Composable
fun HomeScreen(
    pagingItems: LazyPagingItems<UserEntity>,
) {

    Screen(modifier = Modifier.fillMaxSize()) {
        HomeScreenListing(
            users = pagingItems,
            Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun HomeScreenListing(
    users: LazyPagingItems<UserEntity>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { Spacer(modifier = Modifier.size(12.dp)) }

        items(users) {
            it?.let {
                UserCard(
                    user = it,
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                )
            }
        }

        when {
            users.loadState.refresh is LoadState.Loading -> placeholders(15, this)
            users.loadState.append is LoadState.Loading -> placeholders(6, this)
        }

        item { Spacer(modifier = Modifier.size(12.dp)) }
    }
}

@Composable
private fun HomeScreenLoading(
    modifier: Modifier = Modifier
) {

    LazyColumnListing(
        modifier = modifier,
        userScrollEnabled = false,
    ) {
        items(15) {
            UserCardPlaceholder(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
        }
    }
}

private fun placeholders(
    count: Int,
    rowScope: LazyListScope,
) {
    rowScope.apply {
        items(count) {
            UserCardPlaceholder(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
        }
    }
}