package com.arconsis.movietime.bff.moviesdb.api

import com.arconsis.movietime.bff.MovieDto
import com.fasterxml.jackson.annotation.JsonProperty

data class MoviesDbResultsDto(
    val page: Int,
    val results: List<MovieDto>,
    @JsonProperty("total_results") val totalResults: Int,
    @JsonProperty("total_pages") val totalPages: Int
)
