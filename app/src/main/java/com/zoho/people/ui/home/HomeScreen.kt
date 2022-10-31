package com.zoho.people.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.zoho.core.ui.component.Screen
import com.zoho.people.R
import com.zoho.people.core.model.data.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    pagingItems: LazyPagingItems<User>,
    onClickUser: (User) -> Unit,
) {

    Screen(modifier = Modifier.fillMaxSize()) {
        val appBarState = rememberTopAppBarState()
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(appBarState)
        Scaffold(
            topBar = {
                HomeTopAppBar(
                    scrollBehavior = scrollBehavior,
                    onSearchClick = { /* TODO */ }
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            HomeScreenListing(
                users = pagingItems,
                onClickUser = onClickUser,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        scrollBehavior = scrollBehavior,
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        modifier = modifier,
    )
}

@Composable
private fun HomeScreenListing(
    users: LazyPagingItems<User>,
    onClickUser: (User) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { Spacer(modifier = Modifier.size(12.dp)) }

        items(users) {
            it?.let { user ->
                UserCard(
                    user = user,
                    onClick = { onClickUser(user) },
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