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
import com.neo.otaku.ui.theme.OtakuPlusTheme

open class Option(
    val icon: ImageVector,
    val text: String
) {
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
fun OptionCard(
    option: Option,
    modifier: Modifier = Modifier,
    onClick: (Option) -> Unit = { }
) = Card(
    onClick = { onClick(option) },
    modifier = modifier
) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = option.icon,
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = colorScheme.primary
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = option.text,
            style = typography.labelLarge.copy(
                fontSize = 18.sp
            )
        )
    }
}

@ThemesPreview
@Composable
private fun DefaultPreview() {
    OtakuPlusTheme {
        OptionCard(
            option = Option(
                icon = Icons.Rounded.Android,
                text = "text here"
            )
        )
    }
}