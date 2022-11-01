@file:OptIn(ExperimentalMaterial3Api::class)

package com.neo.otaku.ui.screen.source

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.core.Source
import com.neo.otaku.ui.component.SelectableOptions
import com.neo.otaku.ui.screen.source.viewModel.ListType
import com.neo.otaku.ui.screen.source.viewModel.SourceViewModel
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme

@Composable
fun SourceScreen(
    modifier: Modifier = Modifier,
    viewModel: SourceViewModel = viewModel()
) {

    val state = viewModel.uiState.collectAsState().value

    val optionsTranslate = remember { mutableStateOf(0f) }

    val maxOptionTranslate = LocalDensity.current.run { 48.dp.toPx() }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                val delta = available.y
                val newOffset = optionsTranslate.value + delta

                optionsTranslate.value = newOffset.coerceIn(-maxOptionTranslate, 0f)

                return Offset.Zero
            }
        }
    }

    Box(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .nestedScroll(nestedScrollConnection)
    ) {

        VerticalThumbnails(
            thumbnails = state.thumbnails,
            listType = state.listType,
            loadingState = state.loadingState,
            onLoadNextPage = viewModel::loadNextPage,
            modifier = Modifier.fillMaxSize(),
            paddingStart = 56.dp,
        )

        SourceControls(
            selectedListType = state.listType,
            selectedPath = state.selectedPath,
            onChangeListType = {
                viewModel.changeListType(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(top = 16.dp)
                .graphicsLayer {
                    translationY = optionsTranslate.value
                }
        )
    }
}

@Composable
private fun SourceControls(
    modifier: Modifier = Modifier,
    onChangeListType: (ListType) -> Unit,
    selectedPath: Source.Path,
    selectedListType: ListType
) = Row(
    modifier,
    Arrangement.SpaceBetween
) {

    SelectablePath(
        selected = selectedPath
    )

    SelectableOptions(
        options = ListType.all,
        icon = {
            Icon(
                imageVector = it,
                contentDescription = null,
            )
        },
        selected = selectedListType,
        onChange = onChangeListType,
        contentPadding = 4.dp,
        cornerPadding = 8.dp
    )
}

@Composable
private fun SelectablePath(
    selected: Source.Path,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) = OutlinedCard(
    onClick,
    modifier,
    shape = RoundedCornerShape(50)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            text = selected.name,
            Modifier.padding(start = 8.dp),
            style = typography.labelMedium
        )
        Icon(
            Icons.Rounded.ArrowDropDown,
            contentDescription = null,
        )
    }
}

@ThemesPreview
@Composable
private fun DefaultPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            SourceScreen()
        }
    }
}