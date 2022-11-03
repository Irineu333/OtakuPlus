package com.neo.otaku.core

import com.neo.otaku.source.MangaLivre
import com.neo.otaku.source.UnionMangas

object Source {
    data class Details(
        val thumbnail: Thumbnail
    )

    data class Thumbnail(
        val name: String,
        val coverUrl: String,
        val description: String
    )

    data class Page(
        val currentPage: Int,
        val nextPage: Int,
        val thumbnails: List<Thumbnail>,
        val hasNextPage: Boolean
    )

    data class Path(
        val value : String,
        val name : String
    )

    interface Scraping {
        val url : String
        val name: String
        val iconUrl get() = "$url/favicon.ico"

        val paths : List<Path>

        suspend fun getPage(
            page: Int = 1,
            path: Path
        ): Page
        suspend fun getDetails(): Details
    }

    val fonts = listOf(UnionMangas, MangaLivre)
}