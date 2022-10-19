package com.neo.otaku.model

import com.neo.otaku.core.Manga
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

object UnionMangas : Manga.Scraping() {

    override suspend fun getList(): List<Manga.Thumbnail> {

        val document = withContext(Dispatchers.IO) {
            Jsoup.connect("https://unionleitor.top/lista-mangas/visualizacoes").get()
        }

        val block = document.select("div.tamanho-bloco-perfil")
        val elements = block.select("div.lista-mangas-novos")

        return elements.map {
            val name = it.select("a").text()
            val coverUrl = it.select("img").attr("src")

            Manga.Thumbnail(
                name = name,
                coverUrl = coverUrl
            )
        }
    }

    override suspend fun getDetails(): Manga.Details {
        TODO("Not yet implemented")
    }
}