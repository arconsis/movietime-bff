package com.arconsis.movietime.bff.endpoint.dto

data class MovieListItemDto(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val description: String,
    val releaseDate: String,
    val poster: ImageDto?,
)
