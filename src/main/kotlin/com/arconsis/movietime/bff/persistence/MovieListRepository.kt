package com.arconsis.movietime.bff.persistence

import com.arconsis.movietime.bff.model.MovieListModel
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.NotFoundException

@ApplicationScoped
class MovieListRepository : PanacheRepositoryBase<UserMovieListEntity, UUID> {

    fun createMovieList(userId: String, listName: String): MovieListModel {
        val existingList = getMovieListByName(userId, listName)

        if (existingList != null) {
            return existingList
        }

        val newListEntity = UserMovieListEntity(userId, listName)
        persist(newListEntity)
        return newListEntity.toModel()
    }

    fun getMovieListByName(userId: String, listName: String): MovieListModel? {
        val entity = find("userId = ?1 AND listName = ?2", userId, listName).firstResult()
        return entity?.toModel()
    }

    fun updateMovieList(listId: UUID, name: String): MovieListModel {
        val list = findById(listId) ?: throw NotFoundException("Cannot find movie list with id $listId")
        list.listName = name
        return list.toModel()
    }

    fun getMovieIdsForList(userId: String, listName: String): List<Int> {
        val movies = list("userId = ?1 AND listName = ?2", userId, listName)
        return movies.map { it.movieId }
    }
//
//    fun deleteMovieFromList(userId: String, listName: String, movieId: Int) {
//        delete("userId = ?1 AND listName = ?2 AND movieId = ?3", userId, listName, movieId)
//    }
}

fun UserMovieListEntity.toModel(): MovieListModel = MovieListModel(id ?: error("This should not have happend!"), listName)
