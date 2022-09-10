package com.franksap2.chronometer.ui.components

import android.content.res.Configuration
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.franksap2.chronometer.ui.theme.Amber700
import com.franksap2.chronometer.ui.theme.ChronometerComposeTheme

@Composable
fun Chronometer(
    resolution: Int,
    textSize: TextUnit,
    lineSize: Dp,
    lineMaxSize: Dp,
    lineWidth: Dp,
    modifier: Modifier = Modifier,
    animTime: Int = 60_000,
    centerDial: Boolean = false,
    dialScrewSize: Dp = 5.dp,
    dialWidth: Dp = 3.dp
) {

    val surfaceColor = MaterialTheme.colors.surface

    val test = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = Unit) {
        test.animateTo(360f, tween(animTime, easing = LinearEasing))
    }

    Box(modifier = modifier.aspectRatio(1f)) {
        ChronometerBackground(
            resolution = resolution,
            textSize = textSize,
            lineSize = lineSize,
            lineMaxSize = lineMaxSize,
            lineWidth = lineWidth
        )
        Canvas(
            modifier = Modifier.fillMaxSize(1f),
            onDraw = {
                drawDial(test.value, surfaceColor, centerDial, dialScrewSize, dialWidth)
            },
        )
    }

}

private fun DrawScope.drawDial(
    value: Float,
    circleColor: Color,
    centerDial: Boolean,
    dialScrewSize: Dp,
    dialWidth: Dp
) {
    rotate(value) {

        val start = if (centerDial) size.center else size.center.copy(y = size.height * 0.6f)

        drawLine(
            color = Amber700,
            start = start,
            end = Offset(size.width / 2, size.height * 0.1f),
            strokeWidth = dialWidth.toPx(),
            cap = StrokeCap.Round
        )

        drawCircle(circleColor, dialScrewSize.toPx(), size.center)
        drawCircle(Amber700, dialScrewSize.toPx(), size.center, style = Stroke(width = 2.dp.toPx()))
    }

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ChronometerPreview() {
    ChronometerComposeTheme {
        Chronometer(
            resolution = 1,
            textSize = 24.sp,
            lineSize = 10.dp,
            lineMaxSize = 20.dp,
            lineWidth = 2.dp
        )
    }
}