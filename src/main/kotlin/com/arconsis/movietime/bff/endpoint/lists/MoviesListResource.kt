package com.arconsis.movietime.bff.endpoint.lists

import com.arconsis.movietime.bff.endpoint.lists.dto.CreateMovieListDto
import com.arconsis.movietime.bff.endpoint.lists.dto.MovieListDto
import com.arconsis.movietime.bff.endpoint.movies.dto.MoviesUserListDto
import com.arconsis.movietime.bff.lists.MoviesListService
import com.arconsis.movietime.bff.model.MovieListModel
import com.arconsis.movietime.bff.moviesdb.api.MoviesDbService
import org.jboss.resteasy.reactive.RestHeader
import org.jboss.resteasy.reactive.RestPath
import org.jboss.resteasy.reactive.RestQuery
import java.util.*
import javax.ws.rs.*
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MediaType

@Path("/movies/lists")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class MoviesListResource(
    private val moviesListService: MoviesListService
) {

    @GET
    @Path("/lists/{listName}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getMovieList(@RestPath listName: String, @RestQuery user: String, @RestHeader(HttpHeaders.ACCEPT_LANGUAGE) acceptLanguage: String?): MoviesUserListDto {
        return moviesListService.getMovieListForUser(user, listName, acceptLanguage).toUserMovieListResponseDto(listName)
    }

    @POST
    fun addMovieToList(createMovieListDto: CreateMovieListDto, @RestQuery user: String): MovieListDto {
        return moviesListService.createMovieList(user, createMovieListDto.name).toResponseDto()
    }

    @PUT
    @Path("/{listId}")
    fun updateMovieList(@RestPath listId: UUID, createMovieListDto: CreateMovieListDto): MovieListDto {
        return moviesListService.updateMovieList(listId, createMovieListDto.name).toResponseDto()
    }


//    @GET
//    @Path("/lists/{listName}")
//    @Produces(MediaType.APPLICATION_JSON)
//    fun getMovieList(@RestPath listName: String, @RestQuery user: String, @RestHeader(HttpHeaders.ACCEPT_LANGUAGE) acceptLanguage: String?): MoviesUserListDto {
//        return moviesListService.getMovieListForUser(user, listName, acceptLanguage).toUserMovieListResponseDto(listName)
//    }
//
//    @POST
//    @Path("/lists/{listName}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    fun addMovieToList(@RestPath listName: String, @RestQuery user: String, movieId: MovieIdDto) {
//        return moviesListService.addMovieToList(listName, user, movieId.id)
//    }
//
//    @DELETE
//    @Path("/lists/{listName}/{movieId}")
//    fun deleteMovieFromList(@RestPath listName: String, @RestPath movieId: Int, @RestQuery user: String) {
//        return moviesListService.deleteMovieFromList(listName, user, movieId)
//    }
}

private fun MovieListModel.toResponseDto(): MovieListDto = MovieListDto(id, name)
