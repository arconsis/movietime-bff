package com.arconsis.movietime.bff.persistence

import java.util.*
import javax.persistence.*

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(name = "userId_listName_unique", columnNames = ["userId", "listName"])
    ]
)
class UserMovieListEntity() {
    @Id
    @GeneratedValue(generator = "UUID")
    var id: UUID? = null

    @Column(nullable = false)
    lateinit var userId: String

    @Column(nullable = false)
    lateinit var listName: String

    constructor(userId: String, listName: String) : this() {
        this.userId = userId
        this.listName = listName
    }
}
