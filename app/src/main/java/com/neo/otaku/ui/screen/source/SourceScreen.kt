package com.neo.otaku.ui.screen.source

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.twotone.GridView
import androidx.compose.material.icons.twotone.ViewAgenda
import androidx.compose.material.icons.twotone.ViewList
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.core.Option
import com.neo.otaku.ui.component.OptionsChip
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme

@Composable
fun SourceScreen(
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier.fillMaxSize()
) {
    Row(
        Modifier.fillMaxWidth(),
        Arrangement.SpaceBetween
    ) {
        OutlinedCard {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = "Category",
                    Modifier.padding(start = 8.dp),
                    style = typography.labelMedium
                )
                Icon(
                    Icons.Rounded.ArrowDropDown,
                    contentDescription = null,
                )
            }
        }

        val options = listOf(
            Option(
                icon = Icons.TwoTone.GridView,
            ),
            Option(
                icon = Icons.TwoTone.ViewAgenda
            ),
        )

        var selected by remember { mutableStateOf(options[0]) }

        OptionsChip(
            options = options,
            icon = {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                )
            },
            selected = selected,
            onChange = {
                selected = it
            },
            contentPadding = 4.dp,
            cornerPadding = 8.dp
        )
    }
}

@ThemesPreview
@Composable
private fun DefaultPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            SourceScreen(
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}