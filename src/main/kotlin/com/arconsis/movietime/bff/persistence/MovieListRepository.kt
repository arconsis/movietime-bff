package com.arconsis.movietime.bff.persistence

import io.quarkus.hibernate.orm.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MovieListRepository : PanacheRepository<UserMovieListEntity> {

    fun addMovieToList(userId: String, listName: String, movieId: Int) {
        if (!isMovieInUsersList(userId, listName, movieId)) {
            persist(UserMovieListEntity(userId, listName, movieId))
        }
    }

    fun isMovieInUsersList(userId: String, listName: String, movieId: Int): Boolean {
        val numberOfMovies = count("userId = ?1 AND listName = ?2 AND movieId = ?3", userId, listName, movieId)
        return numberOfMovies > 0
    }

    fun getMovieIdsForList(userId: String, listName: String): List<Int> {
        val movies = list("userId = ?1 AND listName = ?2", userId, listName)
        return movies.map { it.movieId }
    }

    fun deleteMovieFromList(userId: String, listName: String, movieId: Int) {
        delete("userId = ?1 AND listName = ?2 AND movieId = ?3", userId, listName, movieId)
    }
}
