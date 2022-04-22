package com.arconsis.movietime.bff.model

import java.time.LocalDate

data class MovieSearchModel(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val description: String,
    val releaseDate: LocalDate?,
    val poster: ImageModel?,
)
