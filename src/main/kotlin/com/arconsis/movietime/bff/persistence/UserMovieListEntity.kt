package com.arconsis.movietime.bff.persistence

import javax.persistence.*

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(name = "userId_listName_movieId_unique", columnNames = ["userId", "listName", "movieId"])
    ]
)
class UserMovieListEntity() {
    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(nullable = false)
    lateinit var userId: String

    @Column(nullable = false)
    lateinit var listName: String

    @Column(nullable = false)
    var movieId: Int = -1

    constructor(userId: String, listName: String, movieId: Int) : this() {
        this.userId = userId
        this.listName = listName
        this.movieId = movieId
    }
}
