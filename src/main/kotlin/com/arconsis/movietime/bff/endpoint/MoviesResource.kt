package com.arconsis.movietime.bff.endpoint

import com.arconsis.movietime.bff.endpoint.dto.MovieDetailDto
import com.arconsis.movietime.bff.endpoint.dto.MovieListItemDto
import com.arconsis.movietime.bff.endpoint.dto.PagedResultsDto
import com.arconsis.movietime.bff.moviesdb.api.MoviesDbService
import org.jboss.resteasy.reactive.Cache
import org.jboss.resteasy.reactive.RestHeader
import org.jboss.resteasy.reactive.RestPath
import org.jboss.resteasy.reactive.RestQuery
import javax.ws.rs.*
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MediaType

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class MoviesResource(
    private val moviesDbService: MoviesDbService,
) {

    @GET
    fun searchMovies(
        @RestQuery query: String?,
        @RestQuery collection: String?,
        @RestQuery page: Int?,
        @RestHeader(HttpHeaders.ACCEPT_LANGUAGE) acceptLanguage: String?
    ): PagedResultsDto<MovieListItemDto> {
        if (query == null && collection == null) {
            throw BadRequestException("Please specify at least query OR collection parameter.")
        }

        val moviesSearchResult = moviesDbService.getMovies(query, collection?.toMovieCollectionName(), page, acceptLanguage)
        return moviesSearchResult.toResponseDtos()
    }

    @GET
    @Path("/{movieId}")
    @Cache(maxAge = 2 * 60 * 60)
    fun getMovieById(@RestPath movieId: Int, @RestHeader(HttpHeaders.ACCEPT_LANGUAGE) acceptLanguage: String?): MovieDetailDto {
        return moviesDbService.getMovieById(movieId, acceptLanguage)?.toResponseDto() ?: throw NotFoundException()
    }
}
