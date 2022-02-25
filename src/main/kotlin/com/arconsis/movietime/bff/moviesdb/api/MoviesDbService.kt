package com.arconsis.movietime.bff.moviesdb.api

import com.arconsis.movietime.bff.model.MovieDetailModel
import com.arconsis.movietime.bff.model.MovieSearchModel
import com.arconsis.movietime.bff.moviesdb.api.dto.MoviesDbDetailDto
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.jboss.resteasy.reactive.RestResponse
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.InternalServerErrorException

private const val locale = "de"

@ApplicationScoped
class MoviesDbService(
    @RestClient private val movieDbApi: MovieDbApi,
    @ConfigProperty(name = "movies-db.api-key") private val apiKey: String,
    private val moviesDbApiMapper: MoviesDbApiMapper
) {

    fun searchMovies(query: String): List<MovieSearchModel> {
        val moviesResponse = movieDbApi.searchMovies(apiKey, locale, query)
        return moviesDbApiMapper.toSearchResultModel(moviesResponse.results)
    }

    fun getMovieById(movieId: Int): MovieDetailModel? {
        val movieResponse = movieDbApi.getMovieById(apiKey, locale, movieId)

        return when (movieResponse.status) {
            RestResponse.StatusCode.OK -> {
                val movie = movieResponse.readEntity(MoviesDbDetailDto::class.java)
                moviesDbApiMapper.toModel(movie)
            }
            RestResponse.StatusCode.NOT_FOUND -> null
            else -> throw InternalServerErrorException("Something went wrong. Movie DB response: ${movieResponse.status}")
        }
    }

}

