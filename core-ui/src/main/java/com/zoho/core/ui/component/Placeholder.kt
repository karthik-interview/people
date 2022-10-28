package com.zoho.core.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder

data class Placeholder(
    val visible: Boolean,
    val color: Color,
    val shape: Shape,
    val highlight: PlaceholderHighlight,
)

object PlaceholderDefaults {

    @Composable
    fun fadingPlaceholder(
        visible: Boolean,
        color: Color = Color.White.copy(alpha = .1f),
        shape: Shape = RoundedCornerShape(4.dp),
        highlight: PlaceholderHighlight = PlaceholderHighlight.fade(
            highlightColor = Color.White.copy(alpha = .3f)
        )
    ): Placeholder {
        return Placeholder(visible, color, shape, highlight)
    }
}

fun Modifier.placeholder(placeholder: Placeholder): Modifier = placeholder(
    visible = placeholder.visible,
    color = placeholder.color,
    shape = placeholder.shape,
    highlight = placeholder.highlight,
)