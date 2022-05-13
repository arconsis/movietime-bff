package com.arconsis.movietime.bff.endpoint

import com.arconsis.movietime.bff.endpoint.dto.error.ErrorDto
import com.arconsis.movietime.bff.model.exceptions.MovieApiErrorException
import org.jboss.logging.Logger
import org.jboss.logging.Logger.Level.ERROR
import org.jboss.resteasy.reactive.RestResponse
import org.jboss.resteasy.reactive.server.ServerExceptionMapper
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response

@ApplicationScoped
class ExceptionMapper {

    private val logger = Logger.getLogger(ExceptionMapper::class.java.name)

    @ServerExceptionMapper
    fun mapException(exception: MovieApiErrorException): Response {
        return Response.status(RestResponse.StatusCode.BAD_REQUEST).entity(ErrorDto(exception.message)).build()
    }

    @ServerExceptionMapper
    fun mapException(exception: WebApplicationException): Response {
        return Response.status(exception.response.status).entity(ErrorDto(exception.message ?: "UNKNOWN ERROR")).build()
    }

    @ServerExceptionMapper
    fun mapException(exception: Throwable): Response {
        logger.log(ERROR, "catched unknown error", exception)
        return Response.status(RestResponse.StatusCode.INTERNAL_SERVER_ERROR).entity(ErrorDto(exception.message ?: "UNKNOWN ERROR")).build()
    }
}
