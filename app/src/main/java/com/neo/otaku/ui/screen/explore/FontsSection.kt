package com.neo.otaku.ui.screen.explore

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.BrokenImage
import androidx.compose.material.icons.twotone.Download
import androidx.compose.material.icons.twotone.Image
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme
import com.neo.otaku.util.extensions.circleShape
import com.neo.otaku.util.extensions.itemPadding
import com.neo.otaku.util.extensions.roundedShape

@Composable
fun FontsSection(
    modifier: Modifier = Modifier,
    fonts: List<RemoteFont>,
    onManageFonts: () -> Unit = {}
) = Column(modifier = modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Icon(
                imageVector = Icons.TwoTone.Download,
                contentDescription = null,
                tint = colorScheme.primary,
                modifier = Modifier
                    .padding(4.dp)
                    .size(18.dp)
            )

            Text(text = "Fontes", color = contentColorFor(colorScheme.background))
        }

        Spacer(modifier = Modifier.weight(1f))

        TextButton(onClick = onManageFonts) {
            Text(text = "GERENCIAR")
        }
    }

    CompositionLocalProvider(
        LocalIndication provides rememberRipple(
            color = colorScheme.primary
        )
    ) {
        LazyColumn {
            itemPadding(
                items = fonts,
                paddingBottom = 4.dp
            ) { font ->

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .roundedShape(4.dp)
                        .clickable {}
                        .padding(2.dp)
                ) {
                    SubcomposeAsyncImage(
                        model = font.iconUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.circleShape(),
                        loading = {
                            Icon(
                                imageVector = Icons.TwoTone.Image,
                                contentDescription = null,
                                tint = colorScheme.primary
                            )
                        },
                        error = {
                            Icon(
                                imageVector = Icons.TwoTone.BrokenImage,
                                contentDescription = null,
                                tint = colorScheme.primary
                            )
                        },
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = font.name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(end = 4.dp),
                        color = contentColorFor(colorScheme.background)
                    )
                }
            }
        }
    }
}

@ThemesPreview
@Composable
private fun DefaultPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            FontsSection(
                fonts = listOf(
                    RemoteFont(name = "Mangá Livre", iconUrl = ""),
                    RemoteFont(name = "Union Mangás", iconUrl = ""),
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}