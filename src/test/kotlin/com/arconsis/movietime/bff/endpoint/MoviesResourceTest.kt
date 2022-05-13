package com.arconsis.movietime.bff.endpoint

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@QuarkusTest
class MoviesResourceTest {

    @ParameterizedTest
    @ValueSource(strings = ["not-valid-collection", ""])
    fun testInvalidCollectionName(invalidCollectionName: String) {
        given()
            .`when`()
            .queryParam("collection", invalidCollectionName)
            .get("/movies")
            .then()
            .assertErrorDto(400, "Collection of name $invalidCollectionName is not supported.")
    }

}
