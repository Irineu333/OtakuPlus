package com.neo.otaku.util.extensions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.CheckCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.neo.otaku.ui.component.Action
import com.neo.otaku.ui.component.AlertCard
import com.neo.otaku.ui.screen.source.viewModel.SourceUiState

fun <E> LazyGridScope.itemsWithPadding(
    items: List<E>,
    paddingEnd: Dp,
    paddingBottom: Dp,
    columns: Int,
    itemContent: @Composable LazyGridItemScope.(item: E) -> Unit
) {
    itemsIndexed(items) { index, item ->

        val lastColum = index.inc() % columns == 0
        val lastRow = index > items.lastIndex - columns

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    end = when {
                        !lastColum -> paddingEnd
                        else -> 0.dp
                    },
                    bottom = when {
                        !lastRow -> paddingBottom
                        else -> 0.dp
                    }
                ).clipToBounds()
        ) {
            itemContent(item)
        }
    }
}

fun LazyGridScope.nextPageLoad(
    state: SourceUiState.State,
    onNextPage: () -> Unit
) {
    item(span = { GridItemSpan(maxLineSpan) }) {
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
