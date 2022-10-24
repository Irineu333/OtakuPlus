package com.neo.otaku.ui.screen.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.BrokenImage
import androidx.compose.material.icons.twotone.Download
import androidx.compose.material.icons.twotone.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
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
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(4.dp)
                    .size(18.dp)
            )

            Text(text = "Fontes")
        }

        Spacer(modifier = Modifier.weight(1f))

        TextButton(onClick = onManageFonts) {
            Text(text = "GERENCIAR")
        }
    }

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
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    error = {
                        Icon(
                            imageVector = Icons.TwoTone.BrokenImage,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = font.name,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 4.dp)
                )
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