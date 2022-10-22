package com.neo.otaku.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Android
import androidx.compose.material.icons.twotone.Image
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Option(
    val icon : ImageVector,
    val text: String,
    val onClick: () -> Unit = {}
)

@Composable
fun OptionCard(
    option: Option,
    modifier: Modifier = Modifier
) =  Card(modifier) {
    Row(modifier = Modifier.padding(16.dp)) {
        Icon(
            imageVector = option.icon,
            contentDescription = null,
            modifier = Modifier.padding(end = 8.dp)
        )

        Text(
            text = option.text,
            fontSize = 16.sp
        )
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    OptionCard(
        option = Option(
            icon = Icons.TwoTone.Image,
            text = "text here"
        )
    )
}