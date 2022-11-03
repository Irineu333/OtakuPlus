package com.neo.otaku.ui.screen.explore.viewModel

import androidx.lifecycle.ViewModel
import com.neo.otaku.core.Source.fonts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExploreViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(getSelectedFonts())
    val uiState = _uiState.asStateFlow()

    private fun getSelectedFonts(): List<FontViewState> {
        return fonts.map { scraping ->
            FontViewState(
                name = scraping.name,
                iconUrl = scraping.iconUrl,
                slug = scraping.slug
            )
        }
    }
}

data class FontViewState(
    val name: String,
    val iconUrl: String,
    val slug: String
)