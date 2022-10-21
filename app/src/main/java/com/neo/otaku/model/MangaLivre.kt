package com.neo.otaku.model

import com.neo.otaku.core.Manga
import com.neo.otaku.util.extensions.firstSubstring
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.select.Elements

object MangaLivre : Manga.Scraping {
    override suspend fun getPage(
        page: Int,
        path: String
    ): Manga.Page {
        val document = withContext(Dispatchers.IO) {
            Jsoup.connect("https://mangalivre.net/series/index/$path?page=$page").get()
        }

        val block = document.select("ul.seriesList")

        val thumbnails = block.getThumbnails()

        return Manga.Page(
            currentPage = page,
            nextPage = page + 1,
            thumbnails = thumbnails,
            hasNextPage = true
        )
    }

    private fun Elements.getThumbnails(): List<Manga.Thumbnail> {
        val elements = select("a.link-block")

        val thumbnails = elements.map {
            val name = it.select("span.series-title").text()

            val coverUrl = it.select("div.cover-image")
                .attr("style")
                .firstSubstring(Regex("(?<=url\\(').+(?='\\))"))

            Manga.Thumbnail(
                name = name,
                coverUrl = coverUrl ?: ""
            )
        }
        return thumbnails
    }

    override suspend fun getDetails(): Manga.Details {
        TODO("Not yet implemented")
    }

    enum class Path(val value: String) {
        RATE("nota"),
    }
}
