package app.pokedex.common.utils

import kotlin.math.floor
import kotlin.math.roundToInt

/**
 * Convert hectograms to pounds.
 * */
fun Int.toLb(): Double {
    return (this * 0.220462 * 10.0).roundToInt() / 10.0
}

/**
 * Convert decimetres to feet and inches.
 * */
fun Int.toFeetAndInches(): String {
    val totalInches = this * 3.93701
    val feet = totalInches.div(12)
    val inches = (feet - floor(feet)).times(12).roundToInt()
    return if (inches < 10) {
        "${floor(feet).roundToInt()}' 0${inches}\""
    } else {
        "${floor(feet).roundToInt()}' ${inches}\""
    }
}