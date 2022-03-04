package com.arconsis.movietime.bff.persistence

import io.quarkus.hibernate.orm.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MovieListRepository : PanacheRepository<UserMovieListEntity> {

    fun saveMovieList(userId: String, listName: String, movieId: Int) = persist(UserMovieListEntity(userId, listName, movieId))

    fun getMovieIdsForList(userId: String, listName: String): List<Int> {
        val movies = list("userId = ?1 AND listName = ?2", userId, listName)
        return movies.map { it.movieId }
    }
}
