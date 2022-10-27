package com.zoho.people.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.zoho.core.ui.theme.PeopleTheme
import com.zoho.people.data.UserUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCard(
    user: UserUi,
    onClick: (UserUi) -> Unit,
    modifier: Modifier = Modifier,
) {

    Card(
        onClick = { onClick(user) },
        modifier = modifier
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.profileUri)
                    .crossfade(true)
                    .build()
            )

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Text(
                text = user.name,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}


@Preview
@Composable
private fun PreviewUserCard() {
    PeopleTheme {
        Column {
            UserCard(
                user = UserUi("John Doe", "https://picsum.photos/200"),
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}