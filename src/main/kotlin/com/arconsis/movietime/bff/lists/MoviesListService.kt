package com.arconsis.movietime.bff.lists

import com.arconsis.movietime.bff.model.MovieListModel
import com.arconsis.movietime.bff.model.MovieListWithMoviesModel
import com.arconsis.movietime.bff.moviesdb.api.MoviesDbService
import com.arconsis.movietime.bff.persistence.MovieListRepository
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class MoviesListService(
    private val moviesDbService: MoviesDbService,
    private val movieListRepository: MovieListRepository
) {

    @Transactional
    fun createMovieList(userId: String, name: String): MovieListModel {
        return movieListRepository.createMovieList(userId, name)
    }

    @Transactional
    fun updateMovieList(listId: UUID, name: String): MovieListModel {
        return movieListRepository.updateMovieList(listId, name)
    }

    @Transactional
    fun getMovieListForUser(userId: String, listName: String, acceptLanguage: String?): MovieListWithMoviesModel? {
        val movieListByName = movieListRepository.getMovieListByName(userId, listName)
        movieListRepository.getMoviesInList(mo)
        return movieListByName
    }
//
//    @Transactional
//    fun addMovieToList(listName: String, userId: String, movieId: Int) {
//        movieListRepository.addMovieToList(userId, listName, movieId)
//    }
//
//    @Transactional
//    fun deleteMovieFromList(listName: String, userId: String, movieId: Int) {
//        movieListRepository.deleteMovieFromList(userId, listName, movieId)
//    }

}
