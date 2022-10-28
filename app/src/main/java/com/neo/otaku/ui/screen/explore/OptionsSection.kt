package com.neo.otaku.ui.screen.explore

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.ui.component.Option
import com.neo.otaku.ui.component.OptionCard
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme
import com.neo.otaku.util.extensions.itemPaddingBetween

@Composable
fun OptionsSection(
    modifier: Modifier = Modifier,
    options: List<Option>,
    columns: Int = 2
) = LazyVerticalGrid(
    columns = GridCells.Fixed(columns),
    modifier = modifier
) {
    itemPaddingBetween(
        items = options,
        paddingEnd = 8.dp,
        paddingBottom = 8.dp,
        columns = columns
    ) { option ->
        OptionCard(
            option = option,
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
                    Option.Historic,
                    Option.Favorites,
                    Option.Saved,
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}