package com.arconsis.movietime.bff.model.exceptions

class MovieApiErrorException(override val message: String) : RuntimeException(message)
