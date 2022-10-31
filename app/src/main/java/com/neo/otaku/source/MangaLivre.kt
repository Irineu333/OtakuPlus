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
            name = "Avaliação",
            value = "nota"
        )
    )

    override suspend fun getPage(
        page: Int,
        path: Source.Path
    ): Source.Page {
        val document = withContext(Dispatchers.IO) {
            Jsoup.connect("$url/series/index/${path.value}?page=$page").get()
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
            val name = it.select("span.series-title").text()

            val coverUrl = it.select("div.cover-image")
                .attr("style")
                .firstSubstring(Regex("(?<=url\\(').+(?='\\))"))

            Source.Thumbnail(
                name = name,
                coverUrl = coverUrl ?: ""
            )
        }
        return thumbnails
    }

    override suspend fun getDetails(): Source.Details {
        TODO("Not yet implemented")
    }
}
