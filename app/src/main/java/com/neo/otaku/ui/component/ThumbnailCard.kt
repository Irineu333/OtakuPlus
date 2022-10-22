package com.neo.otaku.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.BrokenImage
import androidx.compose.material.icons.twotone.Image
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.core.Manga
import com.neo.otaku.ui.theme.OtakuPlusTheme

@Composable
fun ThumbnailCard(
    thumbnail: Manga.Thumbnail,
    modifier: Modifier = Modifier
) = with(thumbnail) {
    Card(modifier) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {

            BoxWithConstraints {
                SubcomposeAsyncImage(
                    model = thumbnail.coverUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(maxWidth * 1.5f)
                        .clip(RoundedCornerShape(8.dp)),
                    loading = {
                        Icon(
                            imageVector = Icons.TwoTone.Image,
                            contentDescription = null,
                            modifier = Modifier.padding(16.dp)
                        )
                    },
                    error = {
                        Icon(
                            imageVector = Icons.TwoTone.BrokenImage,
                            contentDescription = null,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

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
        ThumbnailCard(
            thumbnail = Manga.Thumbnail(
                name = "Name here",
                coverUrl = "",
            ),
            modifier = Modifier.width(120.dp)
        )
    }
}