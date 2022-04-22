package com.arconsis.movietime.bff.endpoint.dto

import java.time.LocalDate

data class MovieListItemDto(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val description: String,
    val releaseDate: LocalDate?,
    val poster: ImageDto?,
)
