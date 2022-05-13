package com.arconsis.movietime.bff.moviesdb.api

import com.arconsis.movietime.bff.moviesdb.api.dto.MoviesDbCollectionName
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.jboss.resteasy.reactive.RestPath
import org.jboss.resteasy.reactive.RestQuery
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Response


@RegisterRestClient(baseUri = "https://api.themoviedb.org/3")
interface MovieDbApi {

    @GET
    @Path("/search/movie")
    fun searchMovies(@RestQuery("api_key") apiKey: String, @RestQuery language: String, @RestQuery query: String, @RestQuery page: Int?): Response

    @GET
    @Path("/movie/{collection}")
    fun getMoviesCollection(@RestQuery("api_key") apiKey: String, @RestQuery language: String, @RestPath collection: MoviesDbCollectionName, @RestQuery page: Int?): Response

    @GET
    @Path("/movie/{movieId}")
    fun getMovieById(@RestQuery("api_key") apiKey: String, @RestQuery language: String, @RestPath movieId: Int): Response


}
