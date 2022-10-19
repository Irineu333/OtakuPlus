package com.neo.otaku.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neo.otaku.annotation.DevicesPreview
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.core.Manga
import com.neo.otaku.ui.component.MangaCard
import com.neo.otaku.ui.screen.home.viewModel.HomeUiState
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme

@Composable
fun HomeContent(
    homeUiState: HomeUiState,
    modifier: Modifier = Modifier
) = Box(
    modifier = modifier
) {
    when (homeUiState) {
        HomeUiState.Error -> {
           Column(
               modifier = Modifier.align(Alignment.Center),
               horizontalAlignment = Alignment.CenterHorizontally
           ) {

               Icon(
                   imageVector = Icons.TwoTone.Error,
                   contentDescription = null,
                   modifier = Modifier.size(100.dp)
               )

               Spacer(modifier = Modifier.height(8.dp))

               Text(
                   text = "Algo deu errado!"
               )
           }
        }

        HomeUiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        is HomeUiState.Valid -> {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp)
            ) {
                items(homeUiState.mangas) { manga ->
                    MangaCard(
                        manga = manga,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}

@DevicesPreview
@ThemesPreview
@Composable
private fun MangaContentValidPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            HomeContent(
                homeUiState = HomeUiState.Valid(
                    mangas = (0..5).map {
                        Manga.Thumbnail(
                            name = "item $it",
                            coverUrl = ""
                        )
                    }
                ),
            )
        }
    }
}

@DevicesPreview
@ThemesPreview
@Composable
private fun MangaContentLoadingPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            HomeContent(
                homeUiState = HomeUiState.Loading,
            )
        }
    }
}

@DevicesPreview
@ThemesPreview
@Composable
private fun MangaContentErrorPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            HomeContent(
                homeUiState = HomeUiState.Error,
            )
        }
    }
}