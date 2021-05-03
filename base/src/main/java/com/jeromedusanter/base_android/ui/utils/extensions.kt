package com.jeromedusanter.base_android.ui.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.Px
import androidx.databinding.Observable
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import java.math.BigDecimal
import java.math.RoundingMode

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
        val inputMethodManager =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    } catch (ignored: RuntimeException) {
        ignored.printStackTrace()
    }
    return false
}

@Px
fun Context.dp2px(dp: Float): Float {
    val metrics = resources.displayMetrics
    return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

/**
 *[scale] represent the number of digit after the decimal point.
 * if the number has no digit after the decimal point, this function will return  a integer
 * Otherwise, this function return the nearest neighbor of the number
 * ex:
 * 3.0000000 -> 3
 * 3.560000000 -> 3.6 if scale == 1 or 3.56 if scale == 2 etc...
 */
fun BigDecimal.toStringRounded(scale: Int): String {
    return if (stripTrailingZeros().scale() <= 0) {
        toInt().toString()
    } else {
        setScale(scale, RoundingMode.HALF_EVEN).toString()
    }
}

