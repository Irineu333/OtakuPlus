package com.neo.otaku.ui.screen.explore

import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.BrokenImage
import androidx.compose.material.icons.twotone.Download
import androidx.compose.material.icons.twotone.Image
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.core.Manga
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme
import com.neo.otaku.util.extensions.clickable
import com.neo.otaku.util.extensions.itemPaddingBetween
import com.neo.otaku.util.extensions.roundedShape

@Composable
fun FontsSection(
    modifier: Modifier = Modifier,
    fonts: List<FontViewState>,
    onManageFonts: () -> Unit = {},
    onOptionClick: (Manga.Scraping) -> Unit = {}
) = Column(modifier = modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Icon(
                imageVector = Icons.TwoTone.Download,
                contentDescription = null,
                tint = colorScheme.primary,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(18.dp)
            )

            Text(
                text = "Fontes",
                style = typography.titleSmall,
                color = contentColorFor(colorScheme.background)
            )
        }

        TextButton(
            onClick = onManageFonts
        ) {
            Text(text = "GERENCIAR")
        }
    }

    LazyColumn {
        itemPaddingBetween(
            items = fonts,
            paddingBottom = 2.dp
        ) { font ->
            FontCard(font = font)
        }
    }
}

@Composable
fun FontCard(
    font: FontViewState,
    modifier: Modifier = Modifier,
    rippleEffect: Indication = rememberRipple(color = colorScheme.primary),
    onClick: () -> Unit = {}
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
        .fillMaxWidth()
        .roundedShape()
        .clickable(
            onClick = onClick,
            indication = rippleEffect,
        )
        .padding(8.dp)
) {
    SubcomposeAsyncImage(
        model = font.iconUrl,
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .size(28.dp)
            .background(
                color = colorScheme.primaryContainer,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(2.dp),
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
        style = typography.labelLarge.copy(
            fontSize = 18.sp
        ),
        color = contentColorFor(
            colorScheme.background
        )
    )
}

@ThemesPreview
@Composable
private fun DefaultPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            FontsSection(
                fonts = listOf(
                    FontViewState(name = "Mangá Livre", iconUrl = ""),
                    FontViewState(name = "Union Mangás", iconUrl = ""),
                ),
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}