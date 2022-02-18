package com.arconsis.movietime.bff

import com.arconsis.movietime.bff.moviesdb.api.MovieDbApi
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.jboss.resteasy.reactive.RestPath
import org.jboss.resteasy.reactive.RestQuery
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/movies")
class ReactiveGreetingResource(
    @RestClient val movieDbApi: MovieDbApi,
    @ConfigProperty(name = "movies-db.api-key") val apiKey: String
) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun searchMovies(@RestQuery query: String): MoviesDto {
        val moviesResponse = movieDbApi.searchMovies(apiKey, "de", query)
        return MoviesDto(moviesResponse.results)
    }

    @GET
    @Path("/{movieId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getMovieById(@RestPath movieId: Int): MovieDto {
        return movies.firstOrNull { it.id == movieId }
            ?: throw NotFoundException()
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createMovie(movieDto: MovieDto): MovieDto {
        return movieDto
    }

    companion object {
        private val movies = listOf(
            MovieDto(1, "Forrest Gump", "", null),
            MovieDto(2, "Spider Man 26", "", "Voll gut")
        )
    }
}


