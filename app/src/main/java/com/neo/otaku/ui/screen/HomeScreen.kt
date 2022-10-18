package com.neo.otaku.ui.screen

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.model.Manga
import com.neo.otaku.ui.component.MangaCard
import com.neo.otaku.ui.theme.OtakuPlusTheme

@Composable
fun HomeScreen(
    mangas: List<Manga>
) = BoxWithConstraints {

    val columns = if (maxHeight > maxWidth) 3 else 6

    LazyVerticalGrid(
        columns = GridCells.Adaptive(maxWidth / columns)
    ) {
        items(mangas) { manga ->
            MangaCard(
                manga = manga,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@ThemesPreview
@Composable
private fun HomeScreenPreview() {
    OtakuPlusTheme {
        HomeScreen(
            listOf(
                Manga(
                    name = "Item 1", coverUrl = ""
                ),
                Manga(
                    name = "Item 2", coverUrl = ""
                ),
                Manga(
                    name = "Item 3", coverUrl = ""
                ),
            )
        )
    }
}