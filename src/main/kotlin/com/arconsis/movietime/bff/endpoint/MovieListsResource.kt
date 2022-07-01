package com.arconsis.movietime.bff.endpoint

import com.arconsis.movietime.bff.endpoint.dto.MovieIdDto
import com.arconsis.movietime.bff.endpoint.dto.MoviesUserListDto
import com.arconsis.movietime.bff.lists.MoviesListService
import io.quarkus.security.Authenticated
import org.jboss.resteasy.reactive.RestHeader
import org.jboss.resteasy.reactive.RestPath
import javax.ws.rs.*
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MediaType

@Authenticated
@Path("/movies/lists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class MovieListsResource(
    private val moviesListService: MoviesListService,
) {

    @GET
    @Path("/{listName}")
    fun getMovieList(@RestPath listName: String, @RestHeader(HttpHeaders.ACCEPT_LANGUAGE) acceptLanguage: String?): MoviesUserListDto {
        return moviesListService.getMovieListForUser(listName, acceptLanguage).toUserMovieListResponseDto(listName)
    }

    @POST
    @Path("/{listName}")
    fun addMovieToList(@RestPath listName: String, movieId: MovieIdDto) {
        return moviesListService.addMovieToList(listName, movieId.id)
    }

    @DELETE
    @Path("/{listName}/{movieId}")
    fun deleteMovieFromList(@RestPath listName: String, @RestPath movieId: Int) {
        return moviesListService.deleteMovieFromList(listName, movieId)
    }
}
