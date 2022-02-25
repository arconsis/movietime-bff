package com.arconsis.movietime.bff.endpoint

import com.arconsis.movietime.bff.endpoint.dto.MovieDetailDto
import com.arconsis.movietime.bff.endpoint.dto.MovieGenreDto
import com.arconsis.movietime.bff.endpoint.dto.MovieListItemDto
import com.arconsis.movietime.bff.endpoint.dto.MoviesListDto
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
class MoviesResource(private val moviesDbService: MoviesDbService) {

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

}

private fun List<MovieSearchModel>.toResponseDtos(): MoviesListDto = MoviesListDto(map { it.toResponseDto() })

private fun MovieSearchModel.toResponseDto(): MovieListItemDto = MovieListItemDto(id, title, originalTitle, description, releaseDate, posterPath)

private fun MovieDetailModel.toResponseDto(): MovieDetailDto {
    val genreDto = genres.map { MovieGenreDto(it.id, it.name) }
    return MovieDetailDto(id, title, originalTitle, description, releaseDate, adult, runtime, tagline, voteAverage, voteCount, posterPath, backdropPath, genreDto)
}
