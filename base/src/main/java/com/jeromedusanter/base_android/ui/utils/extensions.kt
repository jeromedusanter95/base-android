package com.jeromedusanter.base_android.ui.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.Observable
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

internal inline fun <reified T> Any.cast(): T? {
    if (this is T)
        return this
    return null
}

internal fun LocalDate.toFormattedStringWithPattern(pattern: String): String {
    return format(DateTimeFormatter.ofPattern(pattern))
}

internal fun LocalDateTime.toFormattedStringWithPattern(pattern: String): String {
    return format(DateTimeFormatter.ofPattern(pattern))
}

internal fun LocalTime.toFormattedStringWithPattern(pattern: String): String {
    return format(DateTimeFormatter.ofPattern(pattern))
}

fun <T : Observable> T.addOnPropertyChanged(callback: (T) -> Unit) =
    object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(observable: Observable, i: Int) =
            callback(observable as T)
    }.also { addOnPropertyChangedCallback(it) }

fun <T : Observable> T.removePropertyChanged(callback: (T) -> Unit) =
    object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(observable: Observable, i: Int) =
            callback(observable as T)
    }.also { removeOnPropertyChangedCallback(it) }

fun Context.hideKeyboard(view: View): Boolean {
    try {
        val inputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    } catch (ignored: RuntimeException) {
        ignored.printStackTrace()
    }
    return false
}