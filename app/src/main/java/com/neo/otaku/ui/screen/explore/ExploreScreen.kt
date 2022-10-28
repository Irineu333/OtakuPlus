package com.neo.otaku.ui.screen.explore

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.core.Manga
import com.neo.otaku.ui.component.Option
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme

@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel = viewModel(),
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(
            vertical = 16.dp,
            horizontal = 8.dp
        ),
) {
    OptionsSection(
        options = listOf(
            Option.Historic,
            Option.Favorites,
            Option.Saved
        ),
        modifier = Modifier.padding(
            horizontal = 8.dp
        )
    )

    Spacer(modifier = Modifier.height(16.dp))

    val state = viewModel.uiState.collectAsState()

    FontsSection(
        fonts = state.value,
        onManageFonts = {

        },
        onOptionClick = {

        }
    )
}

@ThemesPreview
@Composable
private fun DefaultPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            ExploreScreen()
        }
    }
}