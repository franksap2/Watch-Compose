package com.franksap2.watch.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.franksap2.watch.ui.utils.HOUR
import com.franksap2.watch.ui.utils.MINUTE
import com.franksap2.watch.ui.utils.MINUTES_ON_DAY
import com.franksap2.watch.ui.utils.SECONDS_ON_DAY
import java.time.LocalTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberWatchState(): WatchState {
    val scope = rememberCoroutineScope()
    val state = remember { WatchState(scope) }

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(scope, effect = {
        val lifecycle = lifecycleOwner.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> state.startWatch()
                Lifecycle.Event.ON_PAUSE -> state.startWatch()
                else -> {}
            }
        }
        lifecycle.addObserver(observer)
        onDispose { state.stopWatch() }
    })
    return state
}


@Stable
class WatchState(private val coroutineScope: CoroutineScope) {

    var currentChronometer by mutableStateOf(0L)
        private set

    var currentSeconds by mutableStateOf(0L)
        private set

    var currentMinutes by mutableStateOf(0f)
        private set

    var currentHour by mutableStateOf(0f)
        private set

    var isChronometerEnable by mutableStateOf(false)
        private set

    var isWatchEnable by mutableStateOf(true)
        private set

    private var isPause = false

    fun startWatch() {
        isWatchEnable = true
        coroutineScope.launch {

            val time = LocalTime.now()
            val seconds = time.toSecondOfDay() * 1000

            val startTimer = withFrameMillis { it }

            do {
                currentSeconds = seconds + withFrameMillis { it } - startTimer

                val secondsOfDay = currentSeconds / SECONDS_ON_DAY
                val hour = (secondsOfDay * HOUR) / 1000
                currentHour =  hour
                currentMinutes = (((secondsOfDay * MINUTES_ON_DAY) / 1000) % MINUTE)
            } while (isWatchEnable)

        }
    }

    fun stopWatch() {
        isWatchEnable = false
    }

    fun playChronometer() {
        if (isChronometerEnable) {
            isPause = false
        } else {
            isChronometerEnable = true
            prepareChronometer()
        }
    }

    private fun prepareChronometer() {
        coroutineScope.launch {
            val startTimer = withFrameMillis { it }
            var pauseOffset = 0L
            do {

                val time = withFrameMillis { it } - startTimer

                if (isPause) {
                    pauseOffset = time - currentChronometer
                } else {
                    currentChronometer = time - pauseOffset
                }

            } while (isChronometerEnable)

            currentChronometer = 0L
        }
    }

    fun pause() {
        isPause = true
    }

}