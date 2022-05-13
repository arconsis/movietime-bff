package com.arconsis.movietime.bff.endpoint.dto

import java.time.Instant

data class MovieListItemDto(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val description: String,
    val releaseDate: Instant?,
    val poster: ImageDto?,

    val voteCount: Int,
    val voteAverage: Double
)
