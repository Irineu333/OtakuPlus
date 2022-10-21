package com.neo.otaku.core

object Manga {
    data class Details(
        val thumbnail: Thumbnail
    )

    data class Thumbnail(
        val name: String,
        val coverUrl: String
    )

    data class Page(
        val currentPage : Int,
        val nextPage : Int,
        val thumbnails : List<Thumbnail>,
        val hasNextPage: Boolean
    )

    interface Scraping {

        suspend fun getPage(
            page: Int = 1,
            path: String
        ): Page

        suspend fun getDetails(): Details
    }

    sealed interface State {
        object Loading : State
        object Error : State
        object Lazy : State
        object Finish : State
    }
}