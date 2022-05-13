package com.arconsis.movietime.bff.moviesdb.api

import com.arconsis.movietime.bff.model.*
import com.arconsis.movietime.bff.moviesdb.api.dto.MoviesDbDetailDto
import com.arconsis.movietime.bff.moviesdb.api.dto.MoviesDbGenreDto
import com.arconsis.movietime.bff.moviesdb.api.dto.MoviesDbSearchResultItemDto
import com.arconsis.movietime.bff.moviesdb.api.dto.MoviesDbSearchResultsDto
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MoviesDbApiMapper(private val movieDbImagesConfig: MovieDbImagesConfig) {

    fun toSearchResultModel(searchResult: MoviesDbSearchResultsDto): PagedResultModel<MovieSearchModel> {
        return PagedResultModel(
            searchResult.page,
            searchResult.totalPages,
            searchResult.totalResults,
            searchResult.results.map { it.toModel() }
        )
    }

    private fun MoviesDbSearchResultItemDto.toModel(): MovieSearchModel {
        return MovieSearchModel(id, title, originalTitle, overview, releaseDate, posterPath?.mapImagePathToAbsolute(), voteCount, voteAverage)
    }

    private fun String.mapImagePathToAbsolute(): ImageModel {
        return ImageModel(
            "${movieDbImagesConfig.baseUrl}/${movieDbImagesConfig.thumbnailSize}$this",
            "${movieDbImagesConfig.baseUrl}/${movieDbImagesConfig.originalSize}$this"
        )
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
            movie.posterPath?.mapImagePathToAbsolute(),
            movie.backdropPath?.mapImagePathToAbsolute(),
            modelGenres
        )
    }

    fun List<MoviesDbGenreDto>.toGenreModel(): List<MovieGenreModel> = map { it.toModel() }
    fun MoviesDbGenreDto.toModel(): MovieGenreModel = MovieGenreModel(id, name)
}
