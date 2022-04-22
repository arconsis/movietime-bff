package com.arconsis.movietime.bff.moviesdb.api

import com.arconsis.movietime.bff.model.MovieDetailModel
import com.arconsis.movietime.bff.model.MovieSearchModel
import com.arconsis.movietime.bff.model.PagedResultModel
import com.arconsis.movietime.bff.model.exceptions.MovieApiErrorException
import com.arconsis.movietime.bff.moviesdb.api.dto.MoviesDbDetailDto
import com.arconsis.movietime.bff.moviesdb.api.dto.MoviesDbErrorDto
import com.arconsis.movietime.bff.moviesdb.api.dto.MoviesDbSearchResultsDto
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.jboss.resteasy.reactive.RestResponse
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.InternalServerErrorException


@ApplicationScoped
class MoviesDbService(
    @RestClient private val movieDbApi: MovieDbApi,
    @ConfigProperty(name = "movies-db.api-key") private val apiKey: String,
    private val moviesDbApiMapper: MoviesDbApiMapper
) {

    fun searchMovies(query: String, page: Int?, acceptLanguage: String?): PagedResultModel<MovieSearchModel> {
        val moviesResponse = movieDbApi.searchMovies(apiKey, acceptLanguage ?: DEFAULT_LANGUAGE, query, page)
//        return moviesDbApiMapper.toSearchResultModel(moviesResponse)
//
        return when (moviesResponse.status) {
            RestResponse.StatusCode.OK -> {
                val searchedMovies = moviesResponse.readEntity(MoviesDbSearchResultsDto::class.java)
                moviesDbApiMapper.toSearchResultModel(searchedMovies)
            }
            else -> {
                val errorDto = moviesResponse.readEntity(MoviesDbErrorDto::class.java)
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


    companion object {
        private const val DEFAULT_LANGUAGE = "en"

    }
}

