package com.franksap2.chronometer.ui.components

import android.graphics.Paint
import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.franksap2.chronometer.ui.theme.ChronometerComposeTheme
import com.franksap2.chronometer.ui.theme.Gray100
import com.franksap2.chronometer.ui.utils.MAX_ROTATION
import kotlin.math.cos
import kotlin.math.sin

private const val BACKGROUND_MAKER_COUNT = 120

@Composable
private fun rememberTextPaint(textSize: TextUnit, textColor: Color): TextPaint {

    val textPx = with(LocalDensity.current) { textSize.toPx() }

    return remember {
        TextPaint().apply {
            isAntiAlias = true
            this.textAlign = Paint.Align.CENTER
            this.textSize = textPx
            color = textColor.toArgb()
        }
    }
}

@Composable
fun FaceBackground(
    resolution: Int,
    textSize: TextUnit,
    lineSize: Dp,
    lineMaxSize: Dp,
    lineWidth: Dp,
    faceType: FaceType,
    modifier: Modifier = Modifier
) {

    val textPaint = rememberTextPaint(textSize = textSize, textColor = Color.White)

    Box(modifier) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                drawMakers(
                    textPaint = textPaint,
                    resolution = resolution,
                    textSize = textSize.toPx(),
                    lineSize = lineSize.toPx(),
                    lineMaxSize = lineMaxSize.toPx(),
                    lineWidth = lineWidth.toPx(),
                    faceType = faceType
                )
            },
        )
    }

}

fun DrawScope.drawMakers(
    textPaint: TextPaint,
    resolution: Int,
    textSize: Float,
    lineSize: Float,
    lineMaxSize: Float,
    lineWidth: Float,
    faceType: FaceType
) {

    val markerCount = BACKGROUND_MAKER_COUNT / resolution

    val (lineCountPerMarker, maxCount) = when (faceType) {
        FaceType.MINUTES -> 2 to 30
        FaceType.SECONDS -> 1 to 60
        FaceType.WATCH -> 10 to 12
    }


    for (lineCount in 0 until markerCount) {

        val angle = MAX_ROTATION * lineCount / markerCount.toFloat()

        val xSin = sin(Math.toRadians(angle.toDouble()))
        val yCos = cos(Math.toRadians(angle.toDouble()))

        val endX = size.center.x + xSin * size.center.x
        val endY = size.center.y - yCos * size.center.y
        val endOffset = Offset(endX.toFloat(), endY.toFloat())

        val size = if (lineCount % 2 == 0) lineMaxSize else lineSize

        val addValue = lineCount % 10 == 0
        val alpha = if (addValue) 1f else 0.4f

        val startX = endX + xSin * -size
        val startY = endY - yCos * -size

        val startOffset = Offset(startX.toFloat(), startY.toFloat())

        drawLine(
            color = Gray100,
            start = startOffset,
            end = endOffset,
            strokeWidth = lineWidth,
            alpha = alpha,
            cap = StrokeCap.Round
        )

        if (addValue) {
            val countText = if (lineCount == 0) maxCount else lineCount / lineCountPerMarker

            val textX = startX + xSin * -textSize
            val textY = startY - yCos * -textSize

            textPaint.apply {
                this.textSize = textSize
            }

            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    countText.toString(),
                    textX.toFloat(),
                    textY.toFloat() - (textPaint.descent() + textPaint.ascent()) / 2,
                    textPaint
                )
            }
        }
    }
}

enum class FaceType {
    MINUTES,
    SECONDS,
    WATCH
}

@Preview(showBackground = true)
@Composable
private fun ChronometerBackgroundPreview() {
    ChronometerComposeTheme {

        FaceBackground(
            modifier = Modifier.aspectRatio(1f),
            resolution = 1,
            textSize = 24.sp,
            lineSize = 10.dp,
            lineMaxSize = 20.dp,
            lineWidth = 2.dp,
            faceType = FaceType.WATCH
        )
    }
}
