@file:OptIn(ExperimentalMaterial3Api::class)

package com.neo.otaku.ui.screen.source

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.core.Source
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme

@Composable
fun FiltersSheetContent(
    modifier: Modifier = Modifier,
    paths: List<Source.Path>,
    selected: Source.Path,
    onChangeFilter: (Source.Path) -> Unit = {}
) = Column(
    modifier.fillMaxWidth()
) {

    Divider(
        Modifier
            .padding(top = 10.dp)
            .size(40.dp, 6.dp)
            .align(CenterHorizontally)
            .clip(CircleShape),
        color = colorScheme.onSurfaceVariant
    )

    Text(
        text = "Filtro",
        style = typography.titleLarge,
        modifier = Modifier.padding(16.dp)
    )

    Divider(
        Modifier.fillMaxWidth(),
        color = colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
    )

    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(paths) { item ->
            Card(
                onClick = { onChangeFilter(item) },
                enabled = item != selected,
                colors = CardDefaults.outlinedCardColors(
                    containerColor = Color.Transparent,
                    disabledContainerColor = colorScheme.primaryContainer,
                    disabledContentColor = contentColorFor(colorScheme.primaryContainer)
                )
            ) {
                Text(
                    text = item.name,
                    style = typography.labelMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                )
            }
        }
    }
}

@ThemesPreview
@Composable
private fun DefaultPreview() {

    val paths = (0..5).map {
        Source.Path(
            name = "tag $it",
            value = ""
        )
    }

    var selectedPath by remember { mutableStateOf(paths[0]) }

    OtakuPlusTheme {
        OtakuPlusBackground {
            Surface(color = colorScheme.surfaceVariant) {
                FiltersSheetContent(
                    paths = paths,
                    selected = selectedPath,
                    onChangeFilter = { selectedPath = it }
                )
            }
        }
    }
}