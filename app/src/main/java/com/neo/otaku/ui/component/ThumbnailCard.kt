package com.neo.otaku.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.BrokenImage
import androidx.compose.material.icons.twotone.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.core.Source
import com.neo.otaku.ui.theme.OtakuPlusTheme
import com.neo.otaku.util.extensions.roundedShape

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ThumbnailCard(
    thumbnail: Source.Thumbnail,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            BoxWithConstraints {
                SubcomposeAsyncImage(
                    model = thumbnail.coverUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(maxWidth * 1.5f)
                        .roundedShape(),
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
                text = thumbnail.name,
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
            thumbnail = Source.Thumbnail(
                name = "Name here",
                coverUrl = "",
            ),
            modifier = Modifier.width(120.dp)
        )
    }
}
