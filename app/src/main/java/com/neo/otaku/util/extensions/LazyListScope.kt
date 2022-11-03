package com.neo.otaku.util.extensions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.CheckCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.neo.otaku.ui.component.Action
import com.neo.otaku.ui.component.AlertCard
import com.neo.otaku.ui.screen.source.viewModel.SourceUiState

fun <E> LazyListScope.itemsWithPadding(
    items: List<E>,
    paddingBottom: Dp,
    itemContent: @Composable LazyItemScope.(item: E) -> Unit
) {
    itemsIndexed(items) { index, item ->

        val lastRow = index >= items.lastIndex

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = when {
                        !lastRow -> paddingBottom
                        else -> 0.dp
                    }
                )
        ) {
            itemContent(item)
        }
    }
}

fun LazyListScope.nextPageLoad(
    state: SourceUiState.State,
    onNextPage: () -> Unit
) {
    item {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
        ) {
            when (state) {
                SourceUiState.State.Error -> {
                    AlertCard(
                        action = Action(
                            text = "Tentar novamente",
                            onClick = onNextPage
                        ),
                        message = "Algo deu errado."
                    )
                }
                SourceUiState.State.Finish -> {
                    AlertCard(
                        icon = Icons.TwoTone.CheckCircle,
                        message = "Fim."
                    )
                }
                SourceUiState.State.Lazy, SourceUiState.State.Loading -> {

                    CircularProgressIndicator()

                    if (state is SourceUiState.State.Lazy) {
                        LaunchedEffect(key1 = state) {
                            onNextPage()
                        }
                    }
                }
            }
        }
    }
}
