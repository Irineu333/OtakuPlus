package com.neo.otaku.ui.screen.source.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neo.otaku.core.Source
import com.neo.otaku.source.MangaLivre
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SourceViewModel(
    private val source: Source.Scraping = MangaLivre
) : ViewModel() {

    private val _uiState = MutableStateFlow(SourceUiState(source.paths))
    val uiState = _uiState.asStateFlow()

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        _uiState.update {
            it.copy(
                loadingState = SourceUiState.State.Loading
            )
        }

        viewModelScope.launch {
            runCatching {
                source.getPage(
                    page = uiState.value.nextPage,
                    path = source.paths[0]
                )
            }.onSuccess { page ->
                _uiState.update {
                    it.copy(
                        thumbnails = (it.thumbnails + page.thumbnails).distinct(),
                        lastLoadedPage = page.currentPage,
                        nextPage = page.nextPage,
                        loadingState = if (page.hasNextPage) {
                            SourceUiState.State.Lazy
                        } else {
                            SourceUiState.State.Finish
                        }
                    )
                }
            }.onFailure {
                _uiState.update {
                    it.copy(
                        loadingState = SourceUiState.State.Error
                    )
                }
            }
        }
    }

    fun changeListType(newListType: ListType) {
        _uiState.update {
            it.copy(
                listType = newListType
            )
        }
    }
}