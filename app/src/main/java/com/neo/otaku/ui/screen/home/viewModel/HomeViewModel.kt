package com.neo.otaku.ui.screen.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neo.otaku.core.Manga
import com.neo.otaku.model.MangaLivre
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        _uiState.update {
            it.copy(
                state = Manga.State.Loading
            )
        }

        viewModelScope.launch {
            runCatching {
                MangaLivre.getPage(
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
                            Manga.State.Lazy
                        } else {
                            Manga.State.Finish
                        }
                    )
                }
            }.onFailure {
                _uiState.update {
                    it.copy(
                        state = Manga.State.Error
                    )
                }
            }
        }
    }
}