package com.neo.otaku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.lifecycleScope
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class MainActivity : ComponentActivity() {

    private var title by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OtakuPlusTheme {
                OtakuPlusBackground {
                    Box(contentAlignment = Alignment.Center) {
                        Text("title: $title")
                    }
                }
            }
        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                Jsoup.connect("https://unionleitor.top/lista-mangas").get()
            }.also {
                title = it.title()
            }
        }
    }
}