package com.zoho.people.ui.home

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zoho.core.ui.component.Screen
import com.zoho.people.models.presentation.UserEntity

@Composable
fun HomeScreen(
    state: HomeUiState,
) {

    Screen(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is HomeUiState.Error -> Text(text = state.message)

            is HomeUiState.Listing -> HomeScreenListing(
                users = state.users,
                Modifier.fillMaxWidth()
            )

            is HomeUiState.Loading -> HomeScreenLoading(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun HomeScreenListing(
    users: List<UserEntity>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { Spacer(modifier = Modifier.size(12.dp)) }

        items(users) {
            UserCard(
                user = it,
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
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

@Composable
fun LazyColumnListing(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(12.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    spacing: Dp = 12.dp,
    content: LazyListScope.() -> Unit
) {
    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        flingBehavior = flingBehavior,
        userScrollEnabled = userScrollEnabled,
        content = {
            item { Spacer(modifier = Modifier.size(spacing)) }
            content()
            item { Spacer(modifier = Modifier.size(spacing)) }
        }
    )
}