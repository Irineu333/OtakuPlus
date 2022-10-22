package com.neo.otaku.ui.screen.explore

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.History
import androidx.compose.material.icons.twotone.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.ui.component.Option
import com.neo.otaku.ui.component.OptionCard
import com.neo.otaku.ui.component.OptionsComponent
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme
import com.neo.otaku.util.extensions.itemsWithPadding


@Composable
fun ExploreScreen(
    options : List<Option>
) = Column(
    modifier = Modifier
        .padding(16.dp),
) {
    OptionsComponent(options = options)

    // remote fonts
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
                        text = "Histórico",
                    ),
                    Option(
                        icon = Icons.TwoTone.Favorite,
                        text = "Favoritos",
                    ),
                    Option(
                        icon = Icons.TwoTone.Save,
                        text = "Salvos",
                    ),
                )
            )
        }
    }
}