package com.neo.otaku.ui.screen.source.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neo.otaku.core.Manga
import com.neo.otaku.model.MangaLivre
import com.neo.otaku.ui.screen.home.viewModel.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SourceViewModel(
    private val source: Manga.Scraping
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        _uiState.update {
            it.copy(
                state = HomeUiState.State.Loading
            )
        }

        viewModelScope.launch {
            runCatching {
                source.getPage(
                    page = uiState.value.nextPage,
                    path = MangaLivre.Path.RATE.value
                )
            }.onSuccess { page ->
                _uiState.update {
                    it.copy(
                        thumbnails = (it.thumbnails + page.thumbnails).distinct(),
                        currentPage = page.currentPage,
                        nextPage = page.nextPage,
                        state = if (page.hasNextPage) {
                            HomeUiState.State.Lazy
                        } else {
                            HomeUiState.State.Finish
                        }
                    )
                }
            }.onFailure {
                _uiState.update {
                    it.copy(
                        state = HomeUiState.State.Error
                    )
                }
            }
        }
    }
}