package com.jeromedusanter.base_android.data.utils

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

internal fun LocalDate.toDatabaseFormatString(): String {
    return format(DateTimeFormatter.ISO_DATE_TIME)
}

internal fun LocalDateTime.toDatabaseFormatString(): String {
    return format(DateTimeFormatter.ISO_DATE_TIME)
}

internal fun String.toLocaleDateTime(): LocalDateTime {
    return LocalDateTime.parse(
        this,
        DateTimeFormatter.ISO_DATE_TIME
    )
}

internal fun String.toLocaleDate(): LocalDate {
    return LocalDateTime.parse(
        this,
        DateTimeFormatter.ISO_DATE_TIME
    ).toLocalDate()
}