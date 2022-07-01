package com.arconsis.movietime.bff.endpoint

import com.arconsis.movietime.bff.endpoint.dto.*
import com.arconsis.movietime.bff.model.*
import com.arconsis.movietime.bff.utils.toInstant
import javax.ws.rs.BadRequestException


fun PagedResultModel<MovieSearchModel>.toResponseDtos(): PagedResultsDto<MovieListItemDto> = PagedResultsDto(
    page,
    totalPages,
    totalResults,
    results.map { it.toResponseDto() }
)

fun MovieSearchModel.toResponseDto(): MovieListItemDto = MovieListItemDto(id, title, originalTitle, description, releaseDate?.toInstant(), poster?.toResponseDto(), voteCount, voteAverage)

fun MovieDetailModel.toResponseDto(): MovieDetailDto {
    val genreDto = genres.map { MovieGenreDto(it.id, it.name) }
    return MovieDetailDto(
        id,
        title,
        originalTitle,
        description,
        releaseDate?.toInstant(),
        adult,
        runtime,
        tagline,
        voteAverage,
        voteCount,
        poster?.toResponseDto(),
        backdrop?.toResponseDto(),
        genreDto
    )
}

fun ImageModel.toResponseDto(): ImageDto = ImageDto(thumbnail, original)

fun List<MovieDetailModel>.toResponseDtos(): List<MovieDetailDto> = map { it.toResponseDto() }

fun List<MovieDetailModel>.toUserMovieListResponseDto(listName: String): MoviesUserListDto = MoviesUserListDto(listName, this.toResponseDtos())

fun String.toMovieCollectionName(): MovieCollectionName = when (this) {
    "now-playing" -> MovieCollectionName.NOW_PLAYING
    "popular" -> MovieCollectionName.POPULAR
    "top-rated" -> MovieCollectionName.TOP_RATED
    "upcoming" -> MovieCollectionName.UPCOMING
    else -> throw BadRequestException("Collection of name $this is not supported.")
}
