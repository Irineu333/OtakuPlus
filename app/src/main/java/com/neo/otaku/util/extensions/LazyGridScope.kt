package com.neo.otaku.util.extensions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.CheckCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.neo.otaku.core.Manga
import com.neo.otaku.ui.component.AlertCard
import com.neo.otaku.ui.component.Button
import com.neo.otaku.ui.screen.home.viewModel.HomeUiState

fun <E> LazyGridScope.itemsPaging(
    items: List<E>,
    state: HomeUiState.State,
    hasNextPage: Boolean,
    onNextPage: () -> Unit,
    itemContent: @Composable LazyGridItemScope.(item: E) -> Unit
) {
    items(items = items, itemContent = itemContent)

    item(span = { GridItemSpan(maxLineSpan) }) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
        ) {
            when (state) {
                HomeUiState.State.Error -> {
                    AlertCard(
                        button = Button(
                            text = "Tentar novamente",
                            onClick = onNextPage
                        ),
                        message = "Algo deu errado."
                    )
                }
                HomeUiState.State.Finish -> {
                    AlertCard(
                        icon = Icons.TwoTone.CheckCircle,
                        message = "Fim."
                    )
                }
                HomeUiState.State.Lazy, HomeUiState.State.Loading -> {

                    CircularProgressIndicator()

                    if (state is HomeUiState.State.Lazy) {
                        if (hasNextPage) {
                            LaunchedEffect(key1 = items) {
                                onNextPage()
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <E> LazyGridScope.itemPaddingBetween(
    items: List<E>,
    paddingEnd: Dp,
    paddingBottom: Dp,
    columns: Int,
    itemContent: @Composable LazyGridItemScope.(item: E) -> Unit
) {
    itemsIndexed(items) { index, item ->

        val lastColumItem = index.inc() % columns == 0

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    end = when {
                        !lastColumItem -> paddingEnd
                        else -> 0.dp
                    },
                    bottom = when {
                        index < columns -> paddingBottom
                        else -> 0.dp
                    }
                )
        ) {
            itemContent(item)
        }
    }
}