package com.arconsis.movietime.bff.endpoint.dto

import java.time.Instant
import java.time.OffsetDateTime

data class MovieListItemDto(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val description: String,
    val releaseDate: Instant?,
    val poster: ImageDto?,
)
