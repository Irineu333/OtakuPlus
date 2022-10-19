package com.neo.otaku.ui.screen.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neo.otaku.model.UnionMangas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        load()
    }

    private fun load() = viewModelScope.launch {
        _uiState.value = runCatching {
            HomeUiState.Valid(UnionMangas.getPage())
        }.getOrElse {
            HomeUiState.Error
        }
    }
}