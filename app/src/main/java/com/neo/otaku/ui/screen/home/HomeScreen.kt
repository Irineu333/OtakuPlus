@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.neo.otaku.ui.screen.home

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neo.otaku.annotation.DevicesPreview
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.core.Manga
import com.neo.otaku.ui.component.MangaCard
import com.neo.otaku.ui.theme.OtakuPlusTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) = BoxWithConstraints {
    MangaList(viewModel.state.collectAsStateWithLifecycle().value)
}

@Composable
private fun MangaList(homeState: HomeState) {
    when (homeState) {
        HomeState.Error -> {

        }

        HomeState.Loading -> {

        }

        is HomeState.Valid -> {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp)
            ) {
                items(homeState.mangas) { manga ->
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
private fun MangaListValidPreview() {
    OtakuPlusTheme {
        MangaList(
            homeState = HomeState.Valid(
                mangas = (0..5).map {
                    Manga.Thumbnail(
                        name = "item $it",
                        coverUrl = ""
                    )
                }
            )
        )
    }
}

@DevicesPreview
@ThemesPreview
@Composable
private fun MangaListLoadingPreview() {
    OtakuPlusTheme {
        MangaList(
            homeState = HomeState.Loading
        )
    }
}

@DevicesPreview
@ThemesPreview
@Composable
private fun MangaListErrorPreview() {
    OtakuPlusTheme {
        MangaList(
            homeState = HomeState.Error
        )
    }
}