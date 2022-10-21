package com.neo.otaku.ui.screen.home

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neo.otaku.ui.screen.home.viewModel.HomeViewModel
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) = BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

    val lifecycleState = viewModel.uiState.collectAsState()

    HomeContent(
        homeUiState = lifecycleState.value,
        modifier = Modifier.fillMaxSize(),
        onNextPage = viewModel::loadNextPage
    )
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    OtakuPlusTheme {
        OtakuPlusBackground {
            HomeScreen()
        }
    }
}