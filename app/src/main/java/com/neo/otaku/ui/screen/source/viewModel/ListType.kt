package com.neo.otaku.ui.screen.source.viewModel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.GridView
import androidx.compose.material.icons.twotone.ViewAgenda
import androidx.compose.ui.graphics.vector.ImageVector
import com.neo.otaku.core.Option

sealed class ListType(
    override val icon: ImageVector
) : Option(icon = icon) {

    object Grid : ListType(
        icon = Icons.TwoTone.GridView,
    )

    object List : ListType(
        icon = Icons.TwoTone.ViewAgenda
    )

    companion object {
        val all get() = listOf(Grid, List)
    }
}