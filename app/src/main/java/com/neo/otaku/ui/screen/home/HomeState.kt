package com.neo.otaku.ui.screen.home

import com.neo.otaku.core.Manga

sealed interface HomeState {

    object Loading : HomeState
    object Error : HomeState

    data class Valid(
        val mangas: List<Manga.Thumbnail> = emptyList()
    ) : HomeState
}