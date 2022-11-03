package com.neo.otaku.ui.screen.explore

import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.BrokenImage
import androidx.compose.material.icons.twotone.Image
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.neo.otaku.ui.screen.explore.viewModel.FontViewState
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme
import com.neo.otaku.util.extensions.clickable
import com.neo.otaku.util.extensions.roundedShape


@Composable
internal fun FontCard(
    font: FontViewState,
    modifier: Modifier = Modifier,
    rippleEffect: Indication = rememberRipple(
        color = MaterialTheme.colorScheme.primary
    ),
    onClick: () -> Unit = {}
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
        .fillMaxWidth()
        .roundedShape()
        .clickable(
            onClick = onClick,
            indication = rippleEffect,
        )
        .padding(8.dp)
) {
    SubcomposeAsyncImage(
        model = font.iconUrl,
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .size(28.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(2.dp),
        loading = {
            Icon(
                imageVector = Icons.TwoTone.Image,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        error = {
            Icon(
                imageVector = Icons.TwoTone.BrokenImage,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
    )

    Spacer(modifier = Modifier.width(8.dp))

    Text(
        text = font.name,
        style = typography.labelLarge.copy(
            fontSize = 18.sp
        ),
        color = contentColorFor(
            MaterialTheme.colorScheme.background
        )
    )
}

@Preview
@Composable
private fun DefaultPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            FontCard(
                font = FontViewState(
                    name = "name here",
                    iconUrl = "",
                    slug = ""
                )
            )
        }
    }
}