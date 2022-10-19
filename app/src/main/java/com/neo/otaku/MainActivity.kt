package com.neo.otaku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.neo.otaku.ui.screen.home.HomeScreen
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OtakuPlusTheme {
                OtakuPlusBackground {
                    HomeScreen()
                }
            }
        }
    }
}