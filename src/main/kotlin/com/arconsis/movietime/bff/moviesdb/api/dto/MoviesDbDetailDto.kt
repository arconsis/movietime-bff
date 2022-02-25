package com.arconsis.movietime.bff.moviesdb.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class MoviesDbDetailDto(
    val id: Int,
    val title: String,
    @JsonProperty("original_title")
    val originalTitle: String,
    val overview: String,
    @JsonProperty("release_date")
    val releaseDate: String,
    val adult: Boolean,

    val runtime: Int?,
    val tagline: String?,

    @JsonProperty("vote_average")
    val voteAverage: Double,
    @JsonProperty("vote_count")
    val voteCount: Int,

    @JsonProperty("poster_path")
    val posterPath: String?,
    @JsonProperty("backdrop_path")
    val backdropPath: String?,

    val genres: List<MoviesDbGenreDto>
)
