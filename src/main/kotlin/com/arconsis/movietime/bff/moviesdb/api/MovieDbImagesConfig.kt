package com.arconsis.movietime.bff.moviesdb.api

import io.smallrye.config.ConfigMapping
import io.smallrye.config.WithName

@ConfigMapping(prefix = "movies-db.images")
interface MovieDbImagesConfig {
    @get:WithName("base-url")
    val baseUrl: String
    @get:WithName("thumbnail-size")
    val thumbnailSize: String
    @get:WithName("original-size")
    val originalSize: String
}
