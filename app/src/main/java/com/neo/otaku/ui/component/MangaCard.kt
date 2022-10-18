package com.neo.otaku.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.model.Manga
import com.neo.otaku.ui.theme.OtakuPlusTheme

@Composable
fun MangaCard(
    manga: Manga,
    modifier: Modifier = Modifier
) = with(manga) {
    Card(modifier) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {

            BoxWithConstraints {
                AsyncImage(
                    model = manga.coverUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(maxWidth * 1.5f)
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = name,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@ThemesPreview
@Composable
private fun MangaCardPreview() {
    OtakuPlusTheme {
        MangaCard(
            manga = Manga(
                name = "Name hereerereerer",
                coverUrl = "",
            ),
            modifier = Modifier.width(100.dp)
        )
    }
}