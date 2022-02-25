package com.arconsis.movietime.bff.moviesdb.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class MoviesDbSearchResultItemDto(
    val id: Int,
    val title: String,
    @JsonProperty("original_title")
    val originalTitle: String,
    val overview: String,
    @JsonProperty("release_date")
    val releaseDate: String,
    @JsonProperty("poster_path")
    val posterPath: String?,
)
