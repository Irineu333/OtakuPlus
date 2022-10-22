package com.neo.otaku.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.History
import androidx.compose.material.icons.twotone.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.ui.screen.explore.ExploreScreen
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme
import com.neo.otaku.util.extensions.itemsWithPadding

@Composable
fun OptionsComponent(
    options : List<Option>,
    columns : Int = 2
) = LazyVerticalGrid(
    columns = GridCells.Fixed(columns)
) {
    itemsWithPadding(
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
            ExploreScreen(
                listOf(
                    Option(
                        icon = Icons.TwoTone.History,
                        text = "Historic",
                    ),
                    Option(
                        icon = Icons.TwoTone.Favorite,
                        text = "Favorites",
                    ),
                    Option(
                        icon = Icons.TwoTone.Save,
                        text = "Saved",
                    ),
                )
            )
        }
    }
}