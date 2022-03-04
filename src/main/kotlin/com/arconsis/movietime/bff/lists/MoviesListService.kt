package com.arconsis.movietime.bff.lists

import com.arconsis.movietime.bff.model.MovieDetailModel
import com.arconsis.movietime.bff.moviesdb.api.MoviesDbService
import com.arconsis.movietime.bff.persistence.MovieListRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MoviesListService(
    private val moviesDbService: MoviesDbService,
    private val movieListRepository: MovieListRepository
) {

    fun getMovieListForUser(userId: String, listName: String): List<MovieDetailModel> {
        val movieIds = movieListRepository.getMovieIdsForList(userId, listName)
        return movieIds.mapNotNull { moviesDbService.getMovieById(it) }
    }

}
