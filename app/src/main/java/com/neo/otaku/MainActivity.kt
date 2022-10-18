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
import com.neo.otaku.model.Manga
import com.neo.otaku.ui.screen.HomeScreen
import com.neo.otaku.ui.theme.OtakuPlusBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class MainActivity : ComponentActivity() {

    private var mangas by mutableStateOf<List<Manga>>(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OtakuPlusTheme {
                OtakuPlusBackground {
                    HomeScreen(mangas = mangas)
                }
            }
        }

        lifecycleScope.launch {
            val document = withContext(Dispatchers.IO) {
                Jsoup.connect("https://unionleitor.top/lista-mangas/visualizacoes").get()
            }

            val block = document.select("div.tamanho-bloco-perfil")
            val elements = block.select("div.lista-mangas-novos")

            mangas = elements.map {
                val name = it.select("a").text()
                val coverUrl = it.select("img").attr("src")

                Manga(
                    name = name,
                    coverUrl = coverUrl
                )
            }
        }
    }
}