package com.neo.otaku.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.neo.otaku.core.Source
import com.neo.otaku.ui.screen.explore.ExploreScreen
import com.neo.otaku.ui.screen.source.SourceScreen

@Composable
fun OtakuPlusApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "explore"
    ) {
        composable("explore") {
            ExploreScreen(
                navigationToSource = {
                    navController.navigate("source/$it")
                }
            )
        }

        composable(
            route = "source/{slug}",
            arguments = listOf(
                navArgument("slug") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val slug = backStackEntry.arguments!!.getString("slug")

            SourceScreen(
                source = Source.fonts.first { it.slug == slug }
            )
        }
    }
}