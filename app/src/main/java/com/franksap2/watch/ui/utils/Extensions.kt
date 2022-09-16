package com.franksap2.watch.ui.utils

import java.util.Locale

fun Long.formatTimer(): String {

    val milliSeconds = this % MILLI_SECONDS
    val seconds = this / MILLI_SECONDS % MINUTE
    val minutes = this / MILLI_SECONDS / MINUTE

    return String.format(
        Locale.getDefault(),
        "%02d:%02d,%03d",
        minutes.toInt(),
        seconds.toInt(),
        milliSeconds.toInt()
    ). dropLast(1)
}