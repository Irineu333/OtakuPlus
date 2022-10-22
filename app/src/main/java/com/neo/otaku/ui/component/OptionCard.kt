package com.neo.otaku.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Android
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Option(
    val icon: ImageVector,
    val text: String,
    val onClick: () -> Unit = {}
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionCard(
    option: Option,
    modifier: Modifier = Modifier
) = Card(onClick = option.onClick, modifier) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = option.icon,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 8.dp)
        )

        Text(
            text = option.text,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(bottom = 2.dp)
        )
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    OptionCard(
        option = Option(
            icon = Icons.Rounded.Android,
            text = "text here"
        )
    )
}