package com.neo.otaku.ui.screen.home.viewModel

import com.neo.otaku.core.Manga

data class HomeUiState(
    val thumbnails: List<Manga.Thumbnail> = emptyList(),
    val currentPage: Int? = null,
    val nextPage: Int = 1,
    val state: State = State.Loading
) {
    val isError: Boolean get() = state is State.Error
    val isLoading: Boolean get() = state is State.Loading
    val hasNextPage: Boolean get() = state !is State.Finish

    sealed interface State {
        object Loading : State
        object Error : State
        object Lazy : State
        object Finish : State
    }
}