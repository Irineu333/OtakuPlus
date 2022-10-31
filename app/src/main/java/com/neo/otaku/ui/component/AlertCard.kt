package com.neo.otaku.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Android
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme

data class Action(
    val text: String,
    val onClick: () -> Unit = {}
)

@Composable
fun AlertCard(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    iconTint: Color = LocalContentColor.current,
    message: String,
    action: Action? = null
) = Column(
    modifier = modifier,
    horizontalAlignment = CenterHorizontally
) {
    icon?.let {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .align(CenterHorizontally),
            tint = iconTint
        )

        Spacer(modifier = Modifier.height(8.dp))
    }

    Text(
        text = message,
        fontSize = 22.sp
    )

    action?.let {

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = it.onClick) {
            Text(text = it.text)
        }
    }
}

@ThemesPreview
@Composable
private fun ErrorCardPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            Box {
                AlertCard(
                    modifier = Modifier.align(Alignment.Center),
                    message = "message here",
                    icon = Icons.TwoTone.Android,
                    action = Action(
                        text = "text here"
                    )
                )
            }
        }
    }
}