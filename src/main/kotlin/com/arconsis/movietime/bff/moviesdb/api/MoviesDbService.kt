package com.arconsis.movietime.bff.moviesdb.api

import com.arconsis.movietime.bff.model.MovieCollectionName
import com.arconsis.movietime.bff.model.MovieDetailModel
import com.arconsis.movietime.bff.model.MovieSearchModel
import com.arconsis.movietime.bff.model.PagedResultModel
import com.arconsis.movietime.bff.model.exceptions.MovieApiErrorException
import com.arconsis.movietime.bff.moviesdb.api.dto.MoviesDbCollectionName
import com.arconsis.movietime.bff.moviesdb.api.dto.MoviesDbDetailDto
import com.arconsis.movietime.bff.moviesdb.api.dto.MoviesDbErrorDto
import com.arconsis.movietime.bff.moviesdb.api.dto.MoviesDbSearchResultsDto
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.jboss.resteasy.reactive.RestResponse
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.BadRequestException
import javax.ws.rs.InternalServerErrorException
import javax.ws.rs.core.Response


@ApplicationScoped
class MoviesDbService(
    @RestClient private val movieDbApi: MovieDbApi,
    @ConfigProperty(name = "movies-db.api-key") private val apiKey: String,
    private val moviesDbApiMapper: MoviesDbApiMapper,
) {

    fun getMovies(query: String?, collection: MovieCollectionName?, page: Int?, acceptLanguage: String?): PagedResultModel<MovieSearchModel> {
        val language = acceptLanguage ?: DEFAULT_LANGUAGE

        return if (collection == null) {
            if (query == null) {
                throw BadRequestException("Please specify the query to search!")
            }
            movieDbApi.searchMovies(apiKey, language, query, page)

        } else {
            movieDbApi.getMoviesCollection(apiKey, language, collection.toCollectionName(), page)
        }.parseMoviesResult()
    }

    private fun Response.parseMoviesResult(): PagedResultModel<MovieSearchModel> {
        return when (status) {
            RestResponse.StatusCode.OK -> {
                val searchedMovies = readEntity(MoviesDbSearchResultsDto::class.java)
                moviesDbApiMapper.toSearchResultModel(searchedMovies)
            }
            else -> {
                val errorDto = readEntity(MoviesDbErrorDto::class.java)
                throw MovieApiErrorException("MovieDbApiError: ${errorDto.errors.joinToString(";")}")
            }
        }
    }

    fun getMovieById(movieId: Int, acceptLanguage: String?): MovieDetailModel? {
        val movieResponse = movieDbApi.getMovieById(apiKey, acceptLanguage ?: DEFAULT_LANGUAGE, movieId)

        return when (movieResponse.status) {
            RestResponse.StatusCode.OK -> {
                val movie = movieResponse.readEntity(MoviesDbDetailDto::class.java)
                moviesDbApiMapper.toModel(movie)
            }
            RestResponse.StatusCode.NOT_FOUND -> null
            else -> throw InternalServerErrorException("Something went wrong. Movie DB response: ${movieResponse.status}")
        }
    }

    private fun MovieCollectionName.toCollectionName(): MoviesDbCollectionName = when (this) {
        MovieCollectionName.UPCOMING -> MoviesDbCollectionName.upcoming
        MovieCollectionName.TOP_RATED -> MoviesDbCollectionName.top_rated
        MovieCollectionName.POPULAR -> MoviesDbCollectionName.popular
        MovieCollectionName.NOW_PLAYING -> MoviesDbCollectionName.now_playing
    }

    companion object {
        private const val DEFAULT_LANGUAGE = "en"
    }
}

