package com.arconsis.movietime.bff.moviesdb.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class MoviesDbSearchResultsDto(
    val page: Int,
    val results: List<MoviesDbSearchResultItemDto>,
    @JsonProperty("total_results") val totalResults: Int,
    @JsonProperty("total_pages") val totalPages: Int,
)
