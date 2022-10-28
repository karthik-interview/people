package com.zoho.people.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun DetailScreen(
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Screen(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            IconButton(
                onClick = onClickBack,
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://picsum.photos/800")
                    .crossfade(true)
                    .build()
            )

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Jane Cooper",
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = "London, England",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

            Divider(color = DividerDefaults.color.copy(alpha = .5f))

            ContactInfo(
                modifier = Modifier.fillMaxWidth()
            )

            AboutInfo(Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun ContactInfo(
    modifier: Modifier = Modifier,
) {
    InfoCard(
        label = "Contact info",
        modifier = modifier
    ) {
        InfoItem(
            icon = Icons.Default.Phone,
            value = "+91 73584 14344",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { }
        )

        InfoItem(
            icon = Icons.Default.Email,
            value = "john.doe@google.com",
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