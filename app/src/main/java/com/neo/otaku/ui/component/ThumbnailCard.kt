package com.neo.otaku.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material.icons.twotone.BrokenImage
import androidx.compose.material.icons.twotone.Image
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
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
                style = typography.titleMedium,
                maxLines = 1
            )
        }

        ListType.List -> Row(
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
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

            Spacer(Modifier.width(16.dp))

            var expanded by rememberSaveable { mutableStateOf(false) }

            Column(
                Modifier.height(if (expanded) Dp.Unspecified else 160.dp)
            ) {
                Text(
                    text = thumbnail.name,
                    style = typography.titleLarge
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = thumbnail.description,
                    overflow = TextOverflow.Ellipsis,
                    style = typography.bodyMedium,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clickable {
                            expanded = !expanded
                        }
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
                description = "",
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
                description = "Description here",
            ),
            type = ListType.List
        )
    }
}

