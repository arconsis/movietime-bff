package com.arconsis.movietime.bff.endpoint.movies.dto

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

    val poster: ImageDto?,
    val backdrop: ImageDto?,

    val genres: List<MovieGenreDto>
)
