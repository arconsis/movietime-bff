package com.arconsis.movietime.bff.model

data class PagedResultModel<T>(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val results: List<T>
)
