package com.neo.otaku.model

import com.neo.otaku.core.Source
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.select.Elements

object UnionMangas : Source.Scraping {

    override val url: String = "https://unionleitor.top"
    override val name: String = "Union Mangás"

    override val paths: List<Source.Path> = listOf(
        Source.Path(
            value = "visualizacoes",
            name = "Popular"
        ),
        Source.Path(
            value = "completos",
            name = "Completos"
        )
    )

    override suspend fun getPage(page: Int, path: Source.Path): Source.Page {

        val document = withContext(Dispatchers.IO) {
            Jsoup.connect("$url/lista-mangas/${path.value}/$page").get()
        }

        val block = document.select("div.tamanho-bloco-perfil")

        val thumbnails = block.getThumbnails()

        // val pagination = block.getPagination()

        return Source.Page(
            currentPage = page,
            nextPage = page + 1,
            thumbnails = thumbnails,
            hasNextPage = true
        )
    }

    private fun Elements.getPagination(): Pagination {
        val elements = select("ul.pagination")

        val currentPage = elements.select("li.active")

        return Pagination(
            previousPage = null,
            currentPage = currentPage.text().toInt(),
            nextPage = null
        )
    }

    private fun Elements.getThumbnails(): List<Source.Thumbnail> {
        val elements = select("div.lista-mangas-novos")

        val thumbnails = elements.map {
            val name = it.select("a").text()
            val coverUrl = it.select("img").attr("src")

            Source.Thumbnail(
                name = name,
                coverUrl = coverUrl
            )
        }
        return thumbnails
    }

    override suspend fun getDetails(): Source.Details {
        TODO("Not yet implemented")
    }

    data class Pagination(
        val previousPage: Int?,
        val currentPage: Int,
        val nextPage: Int?
    )
}