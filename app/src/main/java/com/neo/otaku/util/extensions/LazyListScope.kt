package com.neo.otaku.util.extensions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun <E> LazyListScope.itemPaddingBetween(
    items: List<E>,
    paddingBottom: Dp,
    itemContent: @Composable LazyItemScope.(item: E) -> Unit
) {
    itemsIndexed(items) { index, item ->

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = when {
                        index < items.lastIndex -> paddingBottom
                        else -> 0.dp
                    }
                )
        ) {
            itemContent(item)
        }
    }
}