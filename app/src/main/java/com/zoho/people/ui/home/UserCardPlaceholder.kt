package com.zoho.people.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zoho.core.ui.component.PlaceholderDefaults
import com.zoho.core.ui.component.placeholder


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCardPlaceholder(
    modifier: Modifier = Modifier,
) {

    Card(
        onClick = { }, modifier = modifier
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .placeholder(PlaceholderDefaults.fadingPlaceholder(visible = true))
            )

            Column {
                Text(
                    text = "Placeholder",
                    style = MaterialTheme.typography.titleLarge.copy(color = Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .placeholder(PlaceholderDefaults.fadingPlaceholder(visible = true))
                )
                Text(
                    text = "Placeholder",
                    style = MaterialTheme.typography.titleSmall.copy(color = Color.Transparent),
                    modifier = Modifier
                        .alpha(.5f)
                        .fillMaxWidth()
                        .padding(top = 4.dp, bottom = 4.dp)
                        .placeholder(PlaceholderDefaults.fadingPlaceholder(visible = true))
                )
            }
        }
    }
}