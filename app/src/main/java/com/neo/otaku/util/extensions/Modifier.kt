package com.neo.otaku.util.extensions

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.roundedShape(radius : Dp = 8.dp): Modifier = clip(RoundedCornerShape(radius))
fun Modifier.circleShape(): Modifier = clip(CircleShape)