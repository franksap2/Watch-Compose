package com.franksap2.chronometer.ui.components

import android.graphics.Paint
import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
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
import com.franksap2.chronometer.ui.theme.Amber700
import com.franksap2.chronometer.ui.theme.ChronometerComposeTheme
import kotlin.math.cos
import kotlin.math.sin

private const val BACKGROUND_MAKER_SIZE = 240

@Composable
fun rememberTextPaint(textSize: TextUnit, textColor: Color): TextPaint {

    val textPx = with(LocalDensity.current) { textSize.toPx() }

    return remember {
        TextPaint().apply {
            this.textAlign = Paint.Align.CENTER
            this.textSize = textPx
            color = textColor.toArgb()
        }
    }
}

@Composable
fun ChronometerBackground(
    resolution: Int,
    textSize: TextUnit,
    lineSize: Dp,
    lineMaxSize: Dp,
    lineWidth: Dp
) {

    val textPaint = rememberTextPaint(textSize = textSize, textColor = MaterialTheme.colors.onSurface)

    Box {
        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                drawMakers(
                    textPaint = textPaint,
                    resolution = resolution,
                    textSize = textSize.toPx(),
                    lineSize = lineSize.toPx(),
                    lineMaxSize = lineMaxSize.toPx(),
                    lineWidth = lineWidth.toPx()
                )
            },
        )
    /*    Canvas(modifier = Modifier
            .padding(top = 70.dp)
            .size(100.dp)
            .align(Alignment.TopCenter), onDraw = {
            drawMakers(
                textPaint = textPaint,
                resolution = 2,
                textSize = 12.sp.toPx(),
                lineSize = 5.dp.toPx(),
                lineMaxSize = 8.dp.toPx(),
                lineWidth = 1.dp.toPx()
            )
        })*/
    }

}

fun DrawScope.drawMakers(
    textPaint: TextPaint,
    resolution: Int,
    textSize: Float,
    lineSize: Float,
    lineMaxSize: Float,
    lineWidth: Float
) {

    val markerSize = BACKGROUND_MAKER_SIZE / resolution
    val steps = (360 / resolution) / markerSize
    val lineSizeStep = 4 / resolution
    val maxCount = 60 / resolution

    for (lineCount in 0 until markerSize step steps) {

        val angle = 360 * lineCount / markerSize.toFloat()

        val xSin = sin(Math.toRadians(angle.toDouble()))
        val yCos = cos(Math.toRadians(angle.toDouble()))

        val endX = size.center.x + xSin * size.center.x
        val endY = size.center.y - yCos * size.center.y
        val endOffset = Offset(endX.toFloat(), endY.toFloat())

        val size = if (lineCount % lineSizeStep == 0) lineMaxSize else lineSize

        val fiveSecondStep = lineCount % 20 == 0
        val alpha = if (fiveSecondStep) 1f else 0.4f

        val startX = endX + xSin * -size
        val startY = endY - yCos * -size

        val startOffset = Offset(startX.toFloat(), startY.toFloat())

        drawLine(
            color = Amber700,
            start = startOffset,
            end = endOffset,
            strokeWidth = lineWidth,
            alpha = alpha,
            cap = StrokeCap.Round
        )

        if (fiveSecondStep) {
            val countText = if (lineCount == 0) maxCount else lineCount / 4

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

@Preview(showBackground = true)
@Composable
private fun ChronometerBackgroundPreview() {
    ChronometerComposeTheme {

        ChronometerBackground(
            resolution = 1,
            textSize = 24.sp,
            lineSize = 10.dp,
            lineMaxSize = 20.dp,
            lineWidth = 2.dp
        )
    }
}
