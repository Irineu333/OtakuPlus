package com.neo.otaku.ui.screen.source

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neo.otaku.annotation.ThemesPreview
import com.neo.otaku.ui.component.SelectableOptions
import com.neo.otaku.ui.screen.source.viewModel.ListType
import com.neo.otaku.ui.screen.source.viewModel.SourceViewModel
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme

@Composable
fun SourceScreen(
    modifier: Modifier = Modifier,
    viewModel: SourceViewModel = viewModel()
) = Column(
    modifier = modifier.fillMaxSize()
) {

    val state = viewModel.uiState.collectAsState().value

    Row(
        Modifier.fillMaxWidth(),
        Arrangement.SpaceBetween
    ) {
        SelectedChip()
        SelectableOptions(
            options = ListType.all,
            icon = {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                )
            },
            selected = state.listType,
            onChange = {
                viewModel.changeListType(it)
            },
            contentPadding = 4.dp,
            cornerPadding = 8.dp
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    VerticalThumbnails(
        thumbnails = state.thumbnails,
        listType = state.listType,
        loadingState = state.loadingState,
        onLoadNextPage = viewModel::loadNextPage,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun SelectedChip() {
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