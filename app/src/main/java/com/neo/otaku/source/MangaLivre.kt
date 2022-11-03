package com.neo.otaku.source

import com.neo.otaku.core.Source
import com.neo.otaku.util.extensions.firstSubstring
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.select.Elements

object MangaLivre : Source.Scraping {

    override val url: String = "https://mangalivre.net"
    override val name: String = "Manga Livre"

    override val paths: List<Source.Path> = listOf(
        Source.Path(
            name = "Melhor avaliados",
            value = "nota"
        ),
        Source.Path(
            name = "Número de capítulos",
            value = "numero-de-capitulos"
        ),
        Source.Path(
            name = "Mais lidos do site",
            value = "numero-de-leituras/todos/desde-o-comeco"
        ),
        Source.Path(
            name = "Mais lidos do mês",
            value = "numero-de-leituras/todos/mes"
        ),
        Source.Path(
            name = "Mais lidos da semana",
            value = "numero-de-leituras/todos/semana"
        ),
        Source.Path(
            name = "Mais lidos do dia",
            value = "numero-de-leituras/todos/dia"
        ),
    )

    override suspend fun getPage(
        page: Int,
        path: Source.Path
    ): Source.Page {
        val document = withContext(Dispatchers.IO) {
            Jsoup.connect("$url/series/index/${path.value}?page=$page").timeout(5000).get()
        }

        val block = document.select("ul.seriesList")

        val thumbnails = block.getThumbnails()

        return Source.Page(
            currentPage = page,
            nextPage = page + 1,
            thumbnails = thumbnails,
            hasNextPage = true
        )
    }

    private fun Elements.getThumbnails(): List<Source.Thumbnail> {
        val elements = select("a.link-block")

        val thumbnails = elements.map {
            val coverUrl = it.select("div.cover-image")
                .attr("style")
                .firstSubstring(Regex("(?<=url\\(').+(?='\\))"))

            val name = it.select("span.series-title").text()

            val description = it.select("span.series-desc").text()

            Source.Thumbnail(
                name = name,
                coverUrl = coverUrl ?: "",
                description = description
            )
        }
        return thumbnails
    }

    override suspend fun getDetails(): Source.Details {
        TODO("Not yet implemented")
    }
}
