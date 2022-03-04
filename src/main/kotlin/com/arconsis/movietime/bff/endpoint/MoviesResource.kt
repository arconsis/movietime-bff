package com.arconsis.movietime.bff.endpoint

import com.arconsis.movietime.bff.endpoint.dto.*
import com.arconsis.movietime.bff.lists.MoviesListService
import com.arconsis.movietime.bff.model.ImageModel
import com.arconsis.movietime.bff.model.MovieDetailModel
import com.arconsis.movietime.bff.model.MovieSearchModel
import com.arconsis.movietime.bff.moviesdb.api.MoviesDbService
import org.jboss.resteasy.reactive.RestPath
import org.jboss.resteasy.reactive.RestQuery
import javax.ws.rs.GET
import javax.ws.rs.NotFoundException
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/movies")
class MoviesResource(
    private val moviesDbService: MoviesDbService,
    private val moviesListService: MoviesListService
) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun searchMovies(@RestQuery query: String): MoviesListDto {
        val movies = moviesDbService.searchMovies(query)
        return movies.toResponseDtos()
    }

    @GET
    @Path("/{movieId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getMovieById(@RestPath movieId: Int): MovieDetailDto {
        return moviesDbService.getMovieById(movieId)?.toResponseDto() ?: throw NotFoundException()
    }

    @GET
    @Path("/lists/{listName}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getMovieList(@RestPath listName: String, @RestQuery user: String): MoviesUserListDto {
        return moviesListService.getMovieListForUser(user, listName).toUserMovieListResponseDto(listName)
    }
}

private fun List<MovieSearchModel>.toResponseDtos(): MoviesListDto = MoviesListDto(map { it.toResponseDto() })

private fun MovieSearchModel.toResponseDto(): MovieListItemDto = MovieListItemDto(id, title, originalTitle, description, releaseDate, poster?.toResponseDto())

private fun MovieDetailModel.toResponseDto(): MovieDetailDto {
    val genreDto = genres.map { MovieGenreDto(it.id, it.name) }
    return MovieDetailDto(id, title, originalTitle, description, releaseDate, adult, runtime, tagline, voteAverage, voteCount, poster?.toResponseDto(), backdrop?.toResponseDto(), genreDto)
}

private fun ImageModel.toResponseDto(): ImageDto = ImageDto(thumbnail, original)

private fun List<MovieDetailModel>.toResponseDtos(): List<MovieDetailDto> = map { it.toResponseDto() }

private fun List<MovieDetailModel>.toUserMovieListResponseDto(listName: String): MoviesUserListDto = MoviesUserListDto(listName, this.toResponseDtos())
