package com.neo.otaku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.History
import androidx.compose.material.icons.twotone.Save
import com.neo.otaku.ui.component.Option
import com.neo.otaku.ui.screen.explore.ExploreScreen
import com.neo.otaku.ui.screen.explore.RemoteFont
import com.neo.otaku.ui.screen.home.HomeScreen
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OtakuPlusTheme {
                OtakuPlusBackground {
                    ExploreScreen(
                        options = listOf(
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
                        ),
                        fonts = listOf(
                            RemoteFont(
                                name = "Manga Livre",
                                iconUrl = ""
                            ),
                            RemoteFont(
                                name = "Union Mangás",
                                iconUrl = ""
                            )
                        )
                    )
                }
            }
        }
    }
}