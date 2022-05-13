package com.arconsis.movietime.bff.endpoint

import com.arconsis.movietime.bff.endpoint.dto.error.ErrorDto
import io.restassured.http.ContentType
import io.restassured.response.ValidatableResponse
import org.junit.jupiter.api.Assertions

fun ValidatableResponse.assertErrorDto(expectedStatus: Int, expectedMessage: String) {
    val actualError = this.statusCode(expectedStatus)
        .contentType(ContentType.JSON)
        .extract()
        .`as`(ErrorDto::class.java)

    Assertions.assertEquals(expectedMessage, actualError.cause)
}

