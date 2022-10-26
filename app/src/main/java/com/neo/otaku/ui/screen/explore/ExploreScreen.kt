package com.neo.otaku.ui.screen.explore

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.ui.component.Option
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme

data class RemoteFont(
    val name: String,
    val iconUrl: String
)

@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel = viewModel(),
    onNavigate: (String) -> Unit = {},
    fonts: List<RemoteFont> = emptyList(),
    options: List<Option>
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
) {
    OptionsSection(options = options)

    Spacer(modifier = Modifier.height(8.dp))

    val state = viewModel.uiState.collectAsState()

    FontsSection(
        fonts = state.value,
        onManageFonts = {
            onNavigate("remote_fonts")
        }
    )
}

@ThemesPreview
@Composable
private fun DefaultPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            ExploreScreen(
                options = listOf(
                    Option(
                        icon = Icons.TwoTone.History,
                        text = "Histórico",
                    ),
                    Option(
                        icon = Icons.TwoTone.Favorite,
                        text = "Favoritos",
                    ),
                    Option(
                        icon = Icons.TwoTone.Save,
                        text = "Salvos",
                    ),
                ),
                fonts = listOf(
                    RemoteFont(
                        name = "Manga Livre",
                        iconUrl = ""
                    ),
                    RemoteFont(
                        name = "Union Mangás",
                        iconUrl = ""
                    )
                )
            )
        }
    }
}