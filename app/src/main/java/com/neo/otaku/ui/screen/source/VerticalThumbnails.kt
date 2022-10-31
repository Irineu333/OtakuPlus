package com.neo.otaku.ui.screen.source

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.neo.otaku.util.extensions.itemsPagingWithPadding

@Composable
fun VerticalThumbnails(
    thumbnails: List<Source.Thumbnail>,
    loadingState: SourceUiState.State,
    modifier: Modifier = Modifier,
    listType: ListType = ListType.Grid,
    onLoadNextPage: () -> Unit = {}
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
                    columns = GridCells.Fixed(3)
                ) {
                    itemsPagingWithPadding(
                        items = thumbnails,
                        state = loadingState,
                        onNextPage = onLoadNextPage,
                        columns = 3,
                        paddingBottom = 4.dp,
                        paddingBetween = 4.dp
                    ) { thumbnail ->
                        ThumbnailCard(
                            thumbnail = thumbnail
                        )
                    }
                }
            }
            ListType.List -> {
                Text(
                    text = "Soon",
                    modifier = Modifier.align(Alignment.Center)
                )
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
                modifier = Modifier.padding(8.dp),
                thumbnails = (0..5).map {
                    Source.Thumbnail(
                        name = "item $it",
                        coverUrl = ""
                    )
                },
                listType = ListType.Grid,
                loadingState = SourceUiState.State.Finish
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
                modifier = Modifier.padding(8.dp),
                loadingState = SourceUiState.State.Loading,
                thumbnails = (0..5).map {
                    Source.Thumbnail(
                        name = "item $it",
                        coverUrl = ""
                    )
                }
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
                modifier = Modifier.padding(8.dp),
                thumbnails = (0..5).map {
                    Source.Thumbnail(
                        name = "item $it",
                        coverUrl = ""
                    )
                },
                loadingState = SourceUiState.State.Error
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
                loadingState = SourceUiState.State.Error,
                thumbnails = emptyList()
            )
        }
    }
}