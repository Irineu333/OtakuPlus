package com.neo.otaku.model

import com.neo.otaku.core.Manga
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.select.Elements

object UnionMangas : Manga.Scraping {

    override suspend fun getPage(page: Int, path: String): Manga.Page {

        val document = withContext(Dispatchers.IO) {
            Jsoup.connect("https://unionleitor.top/lista-mangas/visualizacoes/$page").get()
        }

        val block = document.select("div.tamanho-bloco-perfil")

        val thumbnails = block.getThumbnails()

        // val pagination = block.getPagination()

        return Manga.Page(
            currentPage = page,
            nextPage = page + 1,
            thumbnails = thumbnails,
            hasNextPage = true
        )
    }

    private fun Elements.getPagination(): Pagination{
        val elements = select("ul.pagination")

        val currentPage = elements.select("li.active")

        return Pagination(
            previousPage = null,
            currentPage = currentPage.text().toInt(),
            nextPage = null
        )
    }

    private fun Elements.getThumbnails(): List<Manga.Thumbnail> {
        val elements = select("div.lista-mangas-novos")

        val thumbnails = elements.map {
            val name = it.select("a").text()
            val coverUrl = it.select("img").attr("src")

            Manga.Thumbnail(
                name = name,
                coverUrl = coverUrl
            )
        }
        return thumbnails
    }

    override suspend fun getDetails(): Manga.Details {
        TODO("Not yet implemented")
    }

    enum class Path(val value: String) {
        POPULAR("visualizacoes"),
        COMPLETE("completos")
    }

    data class Pagination(
        val previousPage : Int?,
        val currentPage : Int,
        val nextPage : Int?
    )
}