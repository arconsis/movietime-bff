package com.arconsis.movietime.bff

import com.fasterxml.jackson.annotation.JsonProperty

data class MovieDto(
    val id: Int,
    val title: String,
    @JsonProperty("original_title") val originalTitle: String,
    val comment: String?
)
