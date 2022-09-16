package com.franksap2.watch.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
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
import com.franksap2.watch.ui.theme.ChronometerComposeTheme

@Composable
fun Chronometer(
    resolution: Int,
    textSize: TextUnit,
    lineSize: Dp,
    lineMaxSize: Dp,
    lineWidth: Dp,
    faceType: FaceType,
    modifier: Modifier = Modifier,
    progressProvider: () -> Float,
    centerDial: Boolean = false,
    dialScrewSize: Dp = 5.dp,
    dialWidth: Dp = 3.dp
) {

    val surfaceColor = MaterialTheme.colors.surface

    Box(modifier = modifier.fillMaxSize()) {
        FaceBackground(
            resolution = resolution,
            textSize = textSize,
            lineSize = lineSize,
            lineMaxSize = lineMaxSize,
            lineWidth = lineWidth,
            faceType = faceType
        )
        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                drawDial(
                    value = progressProvider(),
                    circleColor = surfaceColor,
                    centerDial = centerDial,
                    dialScrewSize = dialScrewSize,
                    dialWidth = dialWidth,
                    dialColor = Color.Black
                )
            }
        )
    }

}

fun DrawScope.drawDial(
    value: Float,
    circleColor: Color,
    centerDial: Boolean,
    dialScrewSize: Dp,
    dialWidth: Dp,
    dialColor: Color,
) {
    rotate(value) {

        val start = if (centerDial) size.center else size.center.copy(y = size.height * 0.6f)

        drawLine(
            color = dialColor,
            start = start,
            end = Offset(size.width / 2, size.height * 0.05f),
            strokeWidth = dialWidth.toPx(),
            cap = StrokeCap.Round
        )

        drawCircle(circleColor, dialScrewSize.toPx(), size.center)
        drawCircle(dialColor, dialScrewSize.toPx(), size.center, style = Stroke(width = 2.dp.toPx()))
    }

}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun ChronometerPreview() {
    ChronometerComposeTheme {
        Chronometer(
            modifier = Modifier.aspectRatio(1f),
            resolution = 1,
            textSize = 24.sp,
            lineSize = 10.dp,
            lineMaxSize = 20.dp,
            lineWidth = 2.dp,
            progressProvider = { 0.3f },
            faceType = FaceType.WATCH
        )
    }
}