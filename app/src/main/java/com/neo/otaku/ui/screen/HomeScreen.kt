package com.neo.otaku.ui.screen

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.neo.otaku.annotation.DevicesPreview
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.model.Manga
import com.neo.otaku.ui.component.MangaCard
import com.neo.otaku.ui.theme.OtakuPlusTheme
import kotlin.math.roundToInt

@Composable
fun HomeScreen(
    mangas: List<Manga>
) = BoxWithConstraints {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(120.dp)
    ) {
        items(mangas) { manga ->
            MangaCard(
                manga = manga,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@DevicesPreview
@ThemesPreview
@Composable
private fun HomeScreenPreview() {
    OtakuPlusTheme {
        HomeScreen(
            (1..5).map {
                Manga(
                    name = "Item $it",
                    coverUrl = ""
                )
            }
        )
    }
}