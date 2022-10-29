package com.neo.otaku.util.extensions

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.roundedShape(radius: Dp = 8.dp): Modifier = clip(RoundedCornerShape(radius))
fun Modifier.circleShape(): Modifier = clip(CircleShape)

fun Modifier.onRippleClick(
    enabled: Boolean = true,
    onClick: () -> Unit
): Modifier = composed {
    clickable(
        onClick = onClick,
        enabled = enabled,
        indication = rememberRipple()
    )
}

fun Modifier.clickable(
    onClick: () -> Unit,
    enabled: Boolean = true,
    indication: Indication,
    onClickLabel: String? = null,
    role: Role? = null
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = onClick,
        role = role,
        indication = indication,
        interactionSource = remember { MutableInteractionSource() }
    )
}