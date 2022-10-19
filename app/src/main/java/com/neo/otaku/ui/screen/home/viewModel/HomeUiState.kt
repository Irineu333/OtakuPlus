package com.neo.otaku.ui.screen.home.viewModel

import com.neo.otaku.core.Manga

sealed interface HomeUiState {

    object Loading : HomeUiState
    object Error : HomeUiState

    data class Valid(
        val mangas: List<Manga.Thumbnail> = emptyList()
    ) : HomeUiState
}