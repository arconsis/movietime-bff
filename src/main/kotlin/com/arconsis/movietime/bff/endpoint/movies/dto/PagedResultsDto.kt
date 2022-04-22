package com.arconsis.movietime.bff.endpoint.movies.dto

data class PagedResultsDto<T>(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val result: List<T>
)
