package com.neo.otaku.util.extensions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neo.otaku.core.Manga
import com.neo.otaku.ui.component.AlertCard
import com.neo.otaku.ui.component.Button
import com.neo.otaku.ui.screen.home.viewModel.HomeUiState

fun <E> LazyGridScope.paginatedItems(
    items: List<E>,
    state: Manga.State,
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
                Manga.State.Error -> {
                    AlertCard(
                        button = Button(
                            text = "Tentar novamente",
                            onClick = onNextPage
                        ),
                        message = "Algo deu errado."
                    )
                }
                Manga.State.Finish -> {
                    AlertCard(
                        message = "FIM"
                    )
                }
                Manga.State.Lazy, Manga.State.Loading-> {

                    CircularProgressIndicator()

                    if (state is Manga.State.Lazy) {
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