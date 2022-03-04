package com.arconsis.movietime.bff.endpoint.dto

data class MoviesUserListDto(
    val listName: String,
    val movies: List<MovieDetailDto>
)
