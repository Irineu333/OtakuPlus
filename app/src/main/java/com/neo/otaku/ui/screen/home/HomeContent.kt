package com.neo.otaku.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.neo.otaku.annotation.DevicesPreview
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.core.Manga
import com.neo.otaku.ui.component.AlertCard
import com.neo.otaku.ui.component.Button
import com.neo.otaku.ui.component.MangaCard
import com.neo.otaku.ui.screen.home.viewModel.HomeUiState
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme
import com.neo.otaku.util.extensions.paginatedItems

@Composable
fun HomeContent(
    homeUiState: HomeUiState,
    modifier: Modifier = Modifier,
    onNextPage: () -> Unit = {}
) = Box(
    modifier = modifier
) {

    when {
        homeUiState.thumbnails.isEmpty() && homeUiState.isError -> {
            AlertCard(
                button = Button(
                    text = "Tentar novamente",
                    onClick = onNextPage
                ),
                icon = Icons.TwoTone.Error,
                iconTint = colorScheme.primary,
                message = "Algo deu errado.",
                modifier = Modifier.align(Alignment.Center)
            )
        }

        homeUiState.thumbnails.isEmpty() && homeUiState.isLoading -> {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        else -> {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp)
            ) {
                paginatedItems(
                    items = homeUiState.thumbnails,
                    state = homeUiState.state,
                    hasNextPage = homeUiState.hasNextPage,
                    onNextPage = onNextPage
                ) { manga ->
                    MangaCard(
                        manga = manga,
                        modifier = Modifier.padding(4.dp)
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
            HomeContent(
                homeUiState = HomeUiState(
                    state = Manga.State.Finish,
                    thumbnails = (0..5).map {
                        Manga.Thumbnail(
                            name = "item $it",
                            coverUrl = ""
                        )
                    }
                ),
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
            HomeContent(
                homeUiState = HomeUiState(
                    state = Manga.State.Loading,
                    thumbnails = (0..5).map {
                        Manga.Thumbnail(
                            name = "item $it",
                            coverUrl = ""
                        )
                    }
                ),
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
            HomeContent(
                homeUiState = HomeUiState(
                    state = Manga.State.Error,
                    thumbnails = (0..5).map {
                        Manga.Thumbnail(
                            name = "item $it",
                            coverUrl = ""
                        )
                    }
                ),
            )
        }
    }
}

@ThemesPreview
@Composable
private fun LoadingPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            HomeContent(
                homeUiState = HomeUiState(
                    state = Manga.State.Loading
                ),
            )
        }
    }
}

@ThemesPreview
@Composable
private fun ErrorPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            HomeContent(
                homeUiState = HomeUiState(
                    state = Manga.State.Error
                ),
            )
        }
    }
}