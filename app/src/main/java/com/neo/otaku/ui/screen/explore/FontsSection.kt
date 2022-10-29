package com.neo.otaku.ui.screen.explore

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.ui.screen.explore.viewModel.FontViewState
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme
import com.neo.otaku.util.extensions.itemPaddingBetween

@Composable
internal fun FontsSection(
    modifier: Modifier = Modifier,
    fonts: List<FontViewState>,
    onManageFonts: () -> Unit = {},
    onOptionClick: () -> Unit = {}
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
            FontCard(
                font = font,
                onClick = { }
            )
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
                    FontViewState(name = "Mangá Livre", iconUrl = ""),
                    FontViewState(name = "Union Mangás", iconUrl = ""),
                ),
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}