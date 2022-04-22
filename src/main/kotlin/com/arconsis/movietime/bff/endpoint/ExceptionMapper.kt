package com.arconsis.movietime.bff.endpoint

import com.arconsis.movietime.bff.endpoint.dto.error.ErrorDto
import com.arconsis.movietime.bff.model.exceptions.MovieApiErrorException
import org.jboss.resteasy.reactive.RestResponse
import org.jboss.resteasy.reactive.server.ServerExceptionMapper
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.Response

@ApplicationScoped
class ExceptionMapper {

    @ServerExceptionMapper
    fun mapException(exception: MovieApiErrorException): Response {
        return Response.status(RestResponse.StatusCode.BAD_REQUEST).entity(ErrorDto(exception.message)).build()
    }

    @ServerExceptionMapper
    fun mapException(exception: Throwable): Response {
        return Response.status(RestResponse.StatusCode.INTERNAL_SERVER_ERROR).entity(ErrorDto(exception.message ?: "UNKNOWN ERROR")).build()
    }
}
