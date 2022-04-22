package com.arconsis.movietime.bff.utils

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

fun LocalDate.toInstant(): Instant = atStartOfDay().toInstant(ZoneOffset.UTC)
