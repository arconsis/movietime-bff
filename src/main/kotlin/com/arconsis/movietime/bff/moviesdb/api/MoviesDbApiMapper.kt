package com.arconsis.movietime.bff.moviesdb.api

import com.arconsis.movietime.bff.model.MovieDetailModel
import com.arconsis.movietime.bff.model.MovieGenreModel
import com.arconsis.movietime.bff.model.MovieSearchModel
import com.arconsis.movietime.bff.moviesdb.api.dto.MoviesDbDetailDto
import com.arconsis.movietime.bff.moviesdb.api.dto.MoviesDbGenreDto
import com.arconsis.movietime.bff.moviesdb.api.dto.MoviesDbSearchResultItemDto
import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MoviesDbApiMapper(@ConfigProperty(name = "movies-db.images.base.thumbnail") private val thumbnailBasePath: String) {

    fun toSearchResultModel(searchItems: List<MoviesDbSearchResultItemDto>): List<MovieSearchModel> = searchItems.map { it.toModel() }

    private fun MoviesDbSearchResultItemDto.toModel(): MovieSearchModel {
        return MovieSearchModel(id, title, originalTitle, overview, releaseDate, mapImagePathToAbsolute(posterPath))
    }

    private fun mapImagePathToAbsolute(path: String?): String? {
        return path?.let { "$thumbnailBasePath$it" }
    }

    fun toModel(movie: MoviesDbDetailDto): MovieDetailModel {
        val modelGenres = movie.genres.toGenreModel()
        return MovieDetailModel(
            movie.id,
            movie.title,
            movie.originalTitle,
            movie.overview,
            movie.releaseDate,
            movie.adult,
            movie.runtime,
            movie.tagline,
            movie.voteAverage,
            movie.voteCount,
            mapImagePathToAbsolute(movie.posterPath),
            mapImagePathToAbsolute(movie.backdropPath),
            modelGenres
        )
    }

    fun List<MoviesDbGenreDto>.toGenreModel(): List<MovieGenreModel> = map { it.toModel() }
    fun MoviesDbGenreDto.toModel(): MovieGenreModel = MovieGenreModel(id, name)
}
