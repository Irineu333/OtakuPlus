package com.neo.otaku.core

object Manga {
    data class Details(
        val thumbnail: Thumbnail
    )

    data class Thumbnail(
        val name: String,
        val coverUrl: String
    )

    abstract class Scraping {
        abstract suspend fun getPage(page : Int = 1): List<Thumbnail>
        abstract suspend fun getDetails(): Details
    }
}