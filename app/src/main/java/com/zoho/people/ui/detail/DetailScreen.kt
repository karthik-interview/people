package com.zoho.people.ui.detail

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.zoho.core.ui.component.Screen
import com.zoho.people.core.model.data.User
import com.zoho.people.presentation.detail.DetailUiState

@Composable
fun DetailScreen(
    state: DetailUiState,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Screen(modifier = modifier) {
        when (state) {
            DetailUiState.Loading -> Text(text = "Loading") // TODO Update the flow
            DetailUiState.UserNotFound -> Text(text = "UserNotFound") // TODO Update the flow
            is DetailUiState.UserDetailFoundUi -> {
                DetailUi(
                    onClickBack = onClickBack,
                    user = state.user,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
private fun DetailUi(
    onClickBack: () -> Unit,
    user: User,
    modifier: Modifier = Modifier,
) {
    val windowSizeClass = calculateWindowSizeClass(LocalContext.current as Activity)
    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded) {
        DetailExpandedUi(onClickBack = onClickBack, user = user, modifier = modifier)
    } else {
        DetailCompactUi(onClickBack = onClickBack, user = user, modifier = modifier)
    }
}


@Composable
private fun DetailCompactUi(
    onClickBack: () -> Unit,
    user: User,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .padding(top = 12.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        BackNavigationIconButton(
            onClickBack = onClickBack,
            modifier = Modifier.align(Alignment.Start)
        )

        ProfileImage(
            uri = user.profileUri,
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
        )

        ProfileMiniInfo(user = user, modifier = Modifier.fillMaxWidth())

        Divider(color = DividerDefaults.color.copy(alpha = .5f))

        ContactInfo(
            phone = user.phone,
            email = user.email,
            modifier = Modifier.fillMaxWidth()
        )

        AboutInfo(Modifier.fillMaxWidth())
    }
}

@Composable
private fun DetailExpandedUi(
    onClickBack: () -> Unit,
    user: User,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(.4f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            BackNavigationIconButton(
                onClickBack = onClickBack,
                modifier = Modifier.align(Alignment.Start)
            )

            ProfileImage(
                uri = user.profileUri,
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
            )

            ProfileMiniInfo(user = user, modifier = Modifier.fillMaxWidth())
        }

        Divider(
            color = DividerDefaults.color.copy(alpha = .5f),
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 4.dp)
                .width(1.dp)
        )

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(top = 12.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            ContactInfo(
                phone = user.phone,
                email = user.email,
                modifier = Modifier.fillMaxWidth()
            )
            ContactInfo(
                phone = user.phone,
                email = user.email,
                modifier = Modifier.fillMaxWidth()
            )

            AboutInfo(Modifier.fillMaxWidth())
        }
    }

}

@Composable
fun ProfileImage(
    uri: String,
    modifier: Modifier = Modifier
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(uri)
            .crossfade(true)
            .build()
    )

    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier,
    )
}

@Composable
fun BackNavigationIconButton(
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClickBack,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }

}

@Composable
fun ProfileMiniInfo(
    user: User,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "${user.firstName} ${user.lastName}",
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = "${user.state}, ${user.country}",
            style = MaterialTheme.typography.bodyMedium,
        )
    }

}

@Composable
fun ContactInfo(
    phone: String,
    email: String,
    modifier: Modifier = Modifier,
) {
    InfoCard(
        label = "Contact info",
        modifier = modifier
    ) {
        InfoItem(
            icon = Icons.Default.Phone,
            value = phone,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { }
        )

        InfoItem(
            icon = Icons.Default.Email,
            value = email,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { }
        )
    }
}

@Composable
fun AboutInfo(
    modifier: Modifier = Modifier,
) {
    InfoCard(
        label = "About",
        modifier = modifier
    ) {
        InfoItem(
            icon = Icons.Default.Cake,
            value = "03 Sep 1999",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { }
        )

        InfoItem(
            icon = Icons.Default.Home,
            value = "Street Details",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { }
        )
    }
}


@Composable
fun InfoCard(
    label: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.secondary.copy(alpha = .1f)),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(12.dp)
        )

        content()
    }
}

@Composable
fun InfoItem(
    icon: ImageVector,
    value: String,
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(12.dp)
                .size(28.dp)
        )

        Text(text = value)
    }

}