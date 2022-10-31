package com.neo.otaku.core

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row as NativeRow

open class Option constructor(
    open val icon: ImageVector? = null,
    open val text: String? = null
) {
    override fun equals(other: Any?): Boolean {
        return if (other is Option) {
            other.icon == icon &&
                    other.text == text
        } else false
    }

    override fun hashCode(): Int {
        var result = icon?.hashCode() ?: 0
        result = 31 * result + (text?.hashCode() ?: 0)
        return result
    }

    @Composable
    fun Row(
        modifier: Modifier = Modifier,
        verticalAlignment: Alignment.Vertical,
        icon: @Composable (ImageVector) -> Unit,
        paddingBetween: Dp = 4.dp,
        text: @Composable (String) -> Unit
    ) = NativeRow(
        modifier = modifier,
        verticalAlignment = verticalAlignment
    ) {
        this@Option.icon?.let {
            icon(it)
        }

        if (this@Option.icon != null && this@Option.text != null) {
            Spacer(modifier = Modifier.width(paddingBetween))
        }

        this@Option.text?.let {
            text(it)
        }
    }
}