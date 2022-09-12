package com.franksap2.chronometer.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberChronometerState(): ChronometerState {
    val scope = rememberCoroutineScope()
    return remember { ChronometerState(scope) }
}

@Stable
class ChronometerState(private val coroutineScope: CoroutineScope) {

    var currentTime by mutableStateOf(0L)
        private set

    var isChronometerEnable by mutableStateOf(false)
        private set

    private var startTimer = 0L
    private var isPause = false

    fun play() {
        if (isChronometerEnable) {
            isPause = false
        } else {
            isChronometerEnable = true
            prepareChronometer()
        }
    }

    private fun prepareChronometer() {
        coroutineScope.launch {
            startTimer = withFrameMillis { it }
            var pauseOffset = 0L
            do {

                val time = withFrameMillis { it } - startTimer

                if (isPause) {
                    pauseOffset = time - currentTime
                } else {
                    currentTime = time - pauseOffset
                }

            } while (isChronometerEnable)

            currentTime = 0L
        }
    }

    fun stop() {
        isChronometerEnable = false
    }

    fun pause() {
        isPause = true
    }

}