package com.neo.otaku.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neo.otaku.model.UnionMangas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state = _state.asStateFlow()

    init {
        load()
    }

    private fun load() = viewModelScope.launch {
        _state.value = runCatching {
            HomeState.Valid(UnionMangas.getList())
        }.getOrElse {
            HomeState.Error
        }
    }
}