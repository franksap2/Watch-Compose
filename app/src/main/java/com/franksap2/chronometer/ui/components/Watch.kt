package com.franksap2.chronometer.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.franksap2.chronometer.ui.state.WatchState
import com.franksap2.chronometer.ui.theme.Gray800
import com.franksap2.chronometer.ui.theme.Red900
import com.franksap2.chronometer.ui.utils.HOUR
import com.franksap2.chronometer.ui.utils.MAX_ROTATION
import com.franksap2.chronometer.ui.utils.MINUTE
import com.franksap2.chronometer.ui.utils.MINUTE_MILLI

@Composable
fun Watch(
    watchState: WatchState,
    modifier: Modifier = Modifier
) {
    val surfaceColor = MaterialTheme.colors.surface

    Box(modifier = modifier.fillMaxSize()) {
        FaceBackground(
            resolution = 1,
            textSize = 24.sp,
            lineSize = 9.dp,
            lineMaxSize = 13.dp,
            lineWidth = 2.dp,
            faceType = FaceType.WATCH
        )
        Canvas(
            modifier = Modifier.fillMaxSize(1f),
            onDraw = {

                drawWatchDials(
                    hours = MAX_ROTATION * (watchState.currentHour / HOUR),
                    minutes = MAX_ROTATION * (watchState.currentMinutes / MINUTE)
                )

                drawDial(
                    value = MAX_ROTATION * (watchState.currentSeconds / MINUTE_MILLI),
                    circleColor = surfaceColor,
                    centerDial = false,
                    dialScrewSize = 5.dp,
                    dialWidth = 3.dp,
                    dialColor = Red900
                )
            }
        )
    }
}

private fun DrawScope.drawWatchDials(hours: Float, minutes: Float) {
    rotate(hours) { watchDial(size.height * 0.2f) }
    rotate(minutes) { watchDial(size.height * 0.1f) }
}

private fun DrawScope.watchDial(dialLength: Float) {
    val dialWidth = 8.dp.toPx()
    val foregroundWidth = 6.dp.toPx()

    drawLine(
        color = Gray800,
        start = size.center,
        end = Offset(size.width / 2, size.height / 2 * 0.8f),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )

    drawLine(
        color = Color(0xFFF2F2F2),
        start = Offset(size.width / 2, size.height / 2 * 0.8f),
        end = Offset(size.width / 2, dialLength),
        strokeWidth = dialWidth,
        cap = StrokeCap.Round
    )

    drawLine(
        color = Gray800,
        start = Offset(size.width / 2, size.height / 2 * 0.8f),
        end = Offset(size.width / 2, dialLength),
        strokeWidth = foregroundWidth,
        cap = StrokeCap.Round
    )

}
