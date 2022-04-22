package com.arconsis.movietime.bff.model

import java.util.*

data class MovieListWithMoviesModel(
    val id: UUID,
    val name: String,
    val movies: List<MovieDetailModel>
)
