package com.arconsis.movietime.bff.model

data class MovieSearchModel(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val description: String,
    val releaseDate: String,
    val posterPath: String?,
)
