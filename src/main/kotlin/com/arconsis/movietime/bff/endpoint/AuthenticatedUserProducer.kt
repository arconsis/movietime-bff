package com.arconsis.movietime.bff.endpoint

import com.arconsis.movietime.bff.model.AuthenticatedUser
import org.eclipse.microprofile.jwt.JsonWebToken
import javax.enterprise.context.RequestScoped

class AuthenticatedUserProducer {

    @RequestScoped
    fun produceAuthenticatedUser(jwt: JsonWebToken): AuthenticatedUser {
        return AuthenticatedUser(jwt.subject)
    }
}
