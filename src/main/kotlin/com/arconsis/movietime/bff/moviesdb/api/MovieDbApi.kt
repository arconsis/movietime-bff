package com.arconsis.movietime.bff.moviesdb.api

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.jboss.resteasy.reactive.RestQuery
import javax.ws.rs.GET
import javax.ws.rs.Path

@RegisterRestClient(baseUri = "https://api.themoviedb.org/4")
interface MovieDbApi {

    @GET
    @Path("/search/movie")
    fun searchMovies(@RestQuery("api_key") apiKey: String, @RestQuery language: String, @RestQuery query: String): MoviesDbResultsDto
}
