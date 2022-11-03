package com.neo.otaku.ui.screen.source.viewModel

import com.neo.otaku.core.Source

data class SourceUiState(
    val paths: List<Source.Path>,
    val thumbnails: List<Source.Thumbnail> = emptyList(),
    val selectedPath: Source.Path = paths[0],
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