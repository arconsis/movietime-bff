package com.arconsis.movietime.bff.lists

import com.arconsis.movietime.bff.model.MovieDetailModel
import com.arconsis.movietime.bff.moviesdb.api.MoviesDbService
import com.arconsis.movietime.bff.persistence.MovieListRepository
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class MoviesListService(
    private val moviesDbService: MoviesDbService,
    private val movieListRepository: MovieListRepository
) {

    fun getMovieListForUser(userId: String, listName: String): List<MovieDetailModel> {
        val movieIds = movieListRepository.getMovieIdsForList(userId, listName)
        return movieIds.mapNotNull { moviesDbService.getMovieById(it) }
    }

    @Transactional
    fun addMovieToList(listName: String, userId: String, movieId: Int) {
        movieListRepository.addMovieToList(userId, listName, movieId)
    }

    @Transactional
    fun deleteMovieFromList(listName: String, userId: String, movieId: Int) {
        movieListRepository.deleteMovieFromList(userId, listName, movieId)
    }
}
