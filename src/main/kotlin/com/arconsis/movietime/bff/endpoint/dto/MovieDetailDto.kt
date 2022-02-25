package com.arconsis.movietime.bff.endpoint.dto

data class MovieDetailDto(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val description: String,
    val releaseDate: String,
    val adult: Boolean,

    val runtime: Int?,
    val tagline: String?,

    val voteAverage: Double,
    val voteCount: Int,

    val posterPath: String?,
    val backdropPath: String?,

    val genres: List<MovieGenreDto>
)
