package com.arconsis.movietime.bff.model

import java.time.LocalDate

data class MovieDetailModel(
    val id: Int,
    val title: String,
    val originalTitle: String,

    val description: String,
    val releaseDate: LocalDate?,
    val adult: Boolean,

    val runtime: Int?,
    val tagline: String?,

    val voteAverage: Double,
    val voteCount: Int,

    val poster: ImageModel?,
    val backdrop: ImageModel?,

    val genres: List<MovieGenreModel>
)
