package com.neo.otaku.ui.screen.home.viewModel

import com.neo.otaku.core.Manga

data class HomeUiState(
    val thumbnails: List<Manga.Thumbnail> = emptyList(),
    val currentPage: Int? = null,
    val nextPage: Int = 1,
    val state: Manga.State = Manga.State.Loading
) {
    val isError: Boolean get() = state is Manga.State.Error
    val isLoading: Boolean get() = state is Manga.State.Loading
    val hasNextPage: Boolean get() = state !is Manga.State.Finish
}