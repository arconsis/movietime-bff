package com.arconsis.movietime.bff.endpoint

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test

@QuarkusTest
class ExceptionMapperTest {

    @Test
    fun testNotFoundException() {
        given()
            .`when`().get("/not-found-endpoint")
            .then()
            .assertErrorDto(404, "HTTP 404 Not Found")
    }
}
