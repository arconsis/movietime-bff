package com.arconsis.movietime.bff.lists

import com.arconsis.movietime.bff.model.AuthenticatedUser
import com.arconsis.movietime.bff.model.MovieDetailModel
import com.arconsis.movietime.bff.moviesdb.api.MoviesDbService
import com.arconsis.movietime.bff.persistence.MovieListRepository
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class MoviesListService(
    private val moviesDbService: MoviesDbService,
    private val movieListRepository: MovieListRepository,
    private val authenticatedUser: AuthenticatedUser,
) {

    fun getMovieListForUser(listName: String, acceptLanguage: String?): List<MovieDetailModel> {
        val movieIds = movieListRepository.getMovieIdsForList(authenticatedUser.userId, listName)
        return movieIds.mapNotNull { moviesDbService.getMovieById(it, acceptLanguage) }
    }

    @Transactional
    fun addMovieToList(listName: String, movieId: Int) {
        movieListRepository.addMovieToList(authenticatedUser.userId, listName, movieId)
    }

    @Transactional
    fun deleteMovieFromList(listName: String, movieId: Int) {
        movieListRepository.deleteMovieFromList(authenticatedUser.userId, listName, movieId)
    }
}
