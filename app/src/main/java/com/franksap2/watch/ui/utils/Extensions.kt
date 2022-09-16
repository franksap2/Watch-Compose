package com.franksap2.watch.ui.utils

import java.util.Locale

private const val MILLI_SECONDS = 1_000
private const val SECONDS = 60

fun Long.formatTimer(): String {

    val milliSeconds = this % MILLI_SECONDS
    val seconds = this / MILLI_SECONDS % SECONDS
    val minutes = this / MILLI_SECONDS / SECONDS

    return String.format(
        Locale.getDefault(),
        "%02d:%02d,%03d",
        minutes,
        seconds,
        milliSeconds
    ). dropLast(1)
}