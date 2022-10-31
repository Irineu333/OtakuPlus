package com.neo.otaku.ui.screen.explore

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.core.Option
import com.neo.otaku.ui.component.Explore
import com.neo.otaku.ui.component.OptionCard
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme
import com.neo.otaku.util.extensions.itemsWithPadding

@Composable
internal fun OptionsSection(
    modifier: Modifier = Modifier,
    options: List<Option>,
    columns: Int = 2
) = LazyVerticalGrid(
    columns = GridCells.Fixed(columns),
    modifier = modifier
) {
    itemsWithPadding(
        items = options,
        paddingContent = 8.dp,
        paddingBottom = 8.dp,
        columns = columns
    ) { option ->
        option.OptionCard(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@ThemesPreview
@Composable
private fun DefaultPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            OptionsSection(
                options = listOf(
                    Explore.Historic,
                    Explore.Favorites,
                    Explore.Saved,
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}