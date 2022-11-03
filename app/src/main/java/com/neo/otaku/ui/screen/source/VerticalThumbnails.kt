package com.neo.otaku.ui.screen.source

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.neo.otaku.annotation.DevicesPreview
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.core.Source
import com.neo.otaku.ui.component.Action
import com.neo.otaku.ui.component.AlertCard
import com.neo.otaku.ui.component.ThumbnailCard
import com.neo.otaku.ui.screen.source.viewModel.ListType
import com.neo.otaku.ui.screen.source.viewModel.SourceUiState
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme
import com.neo.otaku.util.extensions.itemsWithPadding
import com.neo.otaku.util.extensions.nextPageLoad

@Composable
fun VerticalThumbnails(
    thumbnails: List<Source.Thumbnail>,
    loadingState: SourceUiState.State,
    modifier: Modifier = Modifier,
    listType: ListType = ListType.Grid,
    onLoadNextPage: () -> Unit = {},
    listState: LazyGridState = rememberLazyGridState(),
    paddingStart: Dp? = null
) = Box(modifier) {

    val isError = loadingState is SourceUiState.State.Error
    val isLoading = loadingState is SourceUiState.State.Loading

    when {
        thumbnails.isEmpty() && isError -> {
            AlertCard(
                action = Action(
                    text = "Tentar novamente",
                    onClick = onLoadNextPage
                ),
                icon = Icons.TwoTone.Error,
                iconTint = MaterialTheme.colorScheme.primary,
                message = "Algo deu errado.",
                modifier = Modifier.align(Alignment.Center)
            )
        }

        thumbnails.isEmpty() && isLoading -> {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        else -> when (listType) {
            ListType.Grid -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    state = listState
                ) {

                    paddingStart?.let {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Spacer(modifier = Modifier.height(paddingStart))
                        }
                    }

                    itemsWithPadding(
                        items = thumbnails,
                        columns = 3,
                        paddingBottom = 8.dp,
                        paddingEnd = 8.dp
                    ) { thumbnail ->
                        ThumbnailCard(thumbnail, type = listType)
                    }

                    nextPageLoad(
                        state = loadingState,
                        onNextPage = onLoadNextPage
                    )
                }
            }

            ListType.List -> {
                LazyColumn {
                    paddingStart?.let {
                        item {
                            Spacer(modifier = Modifier.height(paddingStart))
                        }
                    }

                    itemsWithPadding(
                        items = thumbnails,
                        paddingBottom = 8.dp,
                    ) { thumbnail ->
                        ThumbnailCard(
                            thumbnail,
                            type = listType
                        )
                    }

                    nextPageLoad(
                        state = loadingState,
                        onNextPage = onLoadNextPage
                    )
                }
            }
        }
    }
}

@DevicesPreview
@ThemesPreview
@Composable
private fun ThumbnailsPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            VerticalThumbnails(
                thumbnails = (0..5).map {
                    Source.Thumbnail(
                        name = "item $it",
                        coverUrl = ""
                    )
                },
                loadingState = SourceUiState.State.Finish,
                modifier = Modifier.padding(8.dp),
                listType = ListType.Grid
            )
        }
    }
}

@DevicesPreview
@ThemesPreview
@Composable
private fun ThumbnailsLoadingPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            VerticalThumbnails(
                thumbnails = (0..5).map {
                    Source.Thumbnail(
                        name = "item $it",
                        coverUrl = ""
                    )
                },
                loadingState = SourceUiState.State.Loading,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@DevicesPreview
@ThemesPreview
@Composable
private fun ThumbnailsErrorPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            VerticalThumbnails(
                thumbnails = (0..5).map {
                    Source.Thumbnail(
                        name = "item $it",
                        coverUrl = ""
                    )
                },
                loadingState = SourceUiState.State.Error,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@ThemesPreview
@Composable
private fun ErrorPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground(Modifier.fillMaxSize()) {
            VerticalThumbnails(
                thumbnails = emptyList(),
                loadingState = SourceUiState.State.Error
            )
        }
    }
}