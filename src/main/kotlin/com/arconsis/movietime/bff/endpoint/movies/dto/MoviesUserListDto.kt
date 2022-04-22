package com.arconsis.movietime.bff.endpoint.movies.dto

data class MoviesUserListDto(
    val listName: String,
    val movies: List<MovieDetailDto>
)
