package com.arconsis.movietime.bff.endpoint.dto

data class PagedResultsDto<T>(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val result: List<T>
)
