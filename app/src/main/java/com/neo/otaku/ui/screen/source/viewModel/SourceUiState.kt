package com.neo.otaku.ui.screen.source.viewModel

import com.neo.otaku.core.Source

data class SourceUiState(
    val thumbnails: List<Source.Thumbnail> = emptyList(),
    val lastLoadedPage: Int? = null,
    val nextPage: Int = 1,
    val loadingState: State = State.Loading,
    val listType: ListType = ListType.Grid
) {
    sealed interface State {
        object Loading : State
        object Error : State
        object Lazy : State
        object Finish : State
    }
}