package com.neo.otaku.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.GridView
import androidx.compose.material.icons.twotone.ViewList
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.core.Option
import com.neo.otaku.ui.screen.source.viewModel.ListType
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme
import com.neo.otaku.util.extensions.onRippleClick

@Composable
fun <T : Option> SelectableOptions(
    options: List<T>,
    modifier: Modifier = Modifier,
    selected: T = options[0],
    onChange: (T) -> Unit = {},
    icon: @Composable (ImageVector) -> Unit = {
        Icon(
            imageVector = it,
            contentDescription = null
        )
    },
    text: @Composable (String) -> Unit = {
        Text(text = it)
    },
    cornerRadiusPercent: Int = 50,
    contentPadding: Dp = 8.dp,
    cornerPadding : Dp = 4.dp
) = OutlinedCard(
    modifier.height(IntrinsicSize.Min),
    RoundedCornerShape(cornerRadiusPercent)
) {

    Row {
        options.forEachIndexed { index, option ->

            val isSelected = selected == option
            val isFirst = index == 0
            val isLast = options.lastIndex == index

            Card(
                shape = when {
                    isFirst -> RoundedCornerShape(
                        topStartPercent = cornerRadiusPercent,
                        topEndPercent = 0,
                        bottomStartPercent = cornerRadiusPercent,
                        bottomEndPercent = 0
                    )

                    isLast -> RoundedCornerShape(
                        topStartPercent = 0,
                        topEndPercent = cornerRadiusPercent,
                        bottomStartPercent = 0,
                        bottomEndPercent = cornerRadiusPercent
                    )

                    else -> RectangleShape
                },
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected)
                        colorScheme.primaryContainer else
                        Color.Unspecified
                ),
                modifier = Modifier.onRippleClick(enabled = !isSelected) {
                    onChange(option)
                },
            ) {
                option.Row(
                    modifier = Modifier.padding(
                        start = if (isFirst) contentPadding + cornerPadding else contentPadding,
                        end = if(isLast) contentPadding + cornerPadding else contentPadding,
                        top = contentPadding,
                        bottom = contentPadding
                    ),
                    verticalAlignment = CenterVertically,
                    icon = icon,
                    text = {
                        CompositionLocalProvider(
                            LocalTextStyle provides typography.labelMedium
                        ) {
                            text(it)
                        }
                    }
                )
            }

            if (index != options.lastIndex) {
                Divider(
                    Modifier
                        .width(1.dp)
                        .fillMaxHeight(),
                    color = colorScheme.outline
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

            val options = ListType.all

            var selected by remember { mutableStateOf(options[0]) }

            SelectableOptions(
                options = options,
                selected = selected,
                onChange = {
                    selected = it
                }
            )
        }
    }
}