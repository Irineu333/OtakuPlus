package com.neo.otaku.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.BrokenImage
import androidx.compose.material.icons.twotone.Image
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.core.Source
import com.neo.otaku.ui.screen.source.viewModel.ListType
import com.neo.otaku.ui.theme.OtakuPlusTheme
import com.neo.otaku.util.extensions.roundedShape

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ThumbnailCard(
    thumbnail: Source.Thumbnail,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    type: ListType
) = Card(
    onClick = onClick,
    modifier = modifier
) {
    when (type) {
        ListType.Grid -> Column(
            Modifier
                .padding(8.dp)
                .width(IntrinsicSize.Min)
        ) {
            SubcomposeAsyncImage(
                model = thumbnail.coverUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp, 180.dp)
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

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = thumbnail.name,
                overflow = TextOverflow.Ellipsis,
                style = typography.titleLarge,
                maxLines = 1
            )
        }
        ListType.List -> Row(
            Modifier.padding(8.dp).fillMaxWidth()
        ) {
            SubcomposeAsyncImage(
                model = thumbnail.coverUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp, 160.dp)
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

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = thumbnail.name,
                    style = typography.titleLarge
                )

                Text(
                    text = "description",
                    overflow = TextOverflow.Ellipsis,
                    style = typography.bodyMedium
                )
            }
        }
    }
}

@ThemesPreview
@Composable
private fun GridPreview() {
    OtakuPlusTheme {
        ThumbnailCard(
            thumbnail = Source.Thumbnail(
                name = "Name here",
                coverUrl = "",
            ),
            type = ListType.Grid
        )
    }
}

@ThemesPreview
@Composable
private fun ListPreview() {
    OtakuPlusTheme {
        ThumbnailCard(
            thumbnail = Source.Thumbnail(
                name = "Name here",
                coverUrl = "",
            ),
            type = ListType.List
        )
    }
}

