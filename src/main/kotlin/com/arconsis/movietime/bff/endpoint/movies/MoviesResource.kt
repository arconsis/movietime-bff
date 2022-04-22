package com.arconsis.movietime.bff.endpoint.movies

import com.arconsis.movietime.bff.endpoint.movies.dto.*
import com.arconsis.movietime.bff.model.ImageModel
import com.arconsis.movietime.bff.model.MovieDetailModel
import com.arconsis.movietime.bff.model.MovieSearchModel
import com.arconsis.movietime.bff.model.PagedResultModel
import com.arconsis.movietime.bff.moviesdb.api.MoviesDbService
import org.jboss.resteasy.reactive.Cache
import org.jboss.resteasy.reactive.RestHeader
import org.jboss.resteasy.reactive.RestPath
import org.jboss.resteasy.reactive.RestQuery
import javax.ws.rs.GET
import javax.ws.rs.NotFoundException
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MediaType

@Path("/movies")
class MoviesResource(private val moviesDbService: MoviesDbService) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun searchMovies(@RestQuery query: String, @RestQuery page: Int?, @RestHeader(HttpHeaders.ACCEPT_LANGUAGE) acceptLanguage: String?): PagedResultsDto<MovieListItemDto> {
        val moviesSearchResult = moviesDbService.searchMovies(query, page, acceptLanguage)
        return moviesSearchResult.toResponseDtos()
    }

    @GET
    @Path("/{movieId}")
    @Cache(maxAge = 2 * 60 * 60)
    @Produces(MediaType.APPLICATION_JSON)
    fun getMovieById(@RestPath movieId: Int, @RestHeader(HttpHeaders.ACCEPT_LANGUAGE) acceptLanguage: String?): MovieDetailDto {
        return moviesDbService.getMovieById(movieId, acceptLanguage)?.toResponseDto() ?: throw NotFoundException()
    }
}

private fun PagedResultModel<MovieSearchModel>.toResponseDtos(): PagedResultsDto<MovieListItemDto> = PagedResultsDto(
    page,
    totalPages,
    totalResults,
    results.map { it.toResponseDto() }
)

private fun MovieSearchModel.toResponseDto(): MovieListItemDto = MovieListItemDto(id, title, originalTitle, description, releaseDate, poster?.toResponseDto())

private fun MovieDetailModel.toResponseDto(): MovieDetailDto {
    val genreDto = genres.map { MovieGenreDto(it.id, it.name) }
    return MovieDetailDto(id, title, originalTitle, description, releaseDate, adult, runtime, tagline, voteAverage, voteCount, poster?.toResponseDto(), backdrop?.toResponseDto(), genreDto)
}

private fun ImageModel.toResponseDto(): ImageDto = ImageDto(thumbnail, original)
