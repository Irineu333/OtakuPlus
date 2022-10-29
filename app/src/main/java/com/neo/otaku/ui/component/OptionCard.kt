package com.neo.otaku.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Android
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.History
import androidx.compose.material.icons.twotone.Save
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.core.Option
import com.neo.otaku.ui.theme.OtakuPlusTheme

object Explore {
    object Historic : Option(
        icon = Icons.Rounded.History,
        text = "Histórico",
    )

    object Favorites : Option(
        icon = Icons.TwoTone.Favorite,
        text = "Favoritos",
    )

    object Saved : Option(
        icon = Icons.Rounded.Save,
        text = "Salvos",
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Option.OptionCard(
    modifier: Modifier = Modifier,
    onClick: (Option) -> Unit = { }
) = Card(
    onClick = { onClick(this) },
    modifier = modifier
) {
    this@OptionCard.Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        icon = { icon ->
            Image(
                imageVector = icon,
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    color = colorScheme.primary
                )
            )
        },
        text = { text ->
            Text(
                text = text,
                style = typography.labelLarge.copy(
                    fontSize = 18.sp
                )
            )
        },
        paddingBetween = 8.dp
    )
}

@ThemesPreview
@Composable
private fun DefaultPreview() {
    OtakuPlusTheme {
        Option(
            icon = Icons.Rounded.Android,
            text = "text here"
        ).OptionCard()
    }
}

@ThemesPreview
@Composable
private fun OnlyIconPreview() {
    OtakuPlusTheme {
        Option(
            icon = Icons.Rounded.Android
        ).OptionCard()
    }
}

@ThemesPreview
@Composable
private fun OnlyTextPreview() {
    OtakuPlusTheme {
        Option(
            text = "text here"
        ).OptionCard()
    }
}