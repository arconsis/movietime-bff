package com.arconsis.movietime.bff.model

import com.arconsis.movietime.bff.utils.AllOpen
import io.quarkus.security.UnauthorizedException

@AllOpen
class AuthenticatedUser(
    val userIdOrNull: String?
) {
    val userId: String
        get() = userIdOrNull ?: throw UnauthorizedException("No token provided or token does not contain userId")


    fun withUserId(block: (userId: String) -> Unit) {
        if (userIdOrNull != null) {
            block(userIdOrNull)
        }
    }
}
