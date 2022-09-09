package com.franksap2.chronometer.ui.components

import android.graphics.Paint
import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.LocalElevationOverlay
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.franksap2.chronometer.ui.theme.Amber700
import com.franksap2.chronometer.ui.theme.ChronometerComposeTheme
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun ChronometerBackground() {

    val backgroundColor = MaterialTheme.colors.surface

    val textColor = MaterialTheme.colors.onSurface

    val textPaint = remember {
        TextPaint().apply {
            this.textAlign = Paint.Align.CENTER
            color = textColor.toArgb()
        }
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .shadow(5.dp, CircleShape),
        onDraw = {
            drawCircle(backgroundColor)
            drawMakers(textPaint)
        },
    )
}

private fun DrawScope.drawMakers(textPaint: TextPaint) {

    val steps = 360 / 240

    for (lineCount in 0 until 240 step steps) {

        val angle = 360 * lineCount / 240f

        val xSin = sin(Math.toRadians(angle.toDouble()))
        val yCos = cos(Math.toRadians(angle.toDouble()))

        val endX = size.center.x + xSin * size.center.x
        val endY = size.center.y - yCos * size.center.y
        val endOffset = Offset(endX.toFloat(), endY.toFloat())

        val size = if (lineCount % 4 == 0) 20.dp.toPx() else 10.dp.toPx()

        val fiveSecondStep = lineCount % 20 == 0
        val alpha = if (fiveSecondStep) 1f else 0.4f

        val startX = endX + xSin * -size
        val startY = endY - yCos * -size

        val startOffset = Offset(startX.toFloat(), startY.toFloat())

        drawLine(
            color = Amber700,
            start = startOffset,
            end = endOffset,
            strokeWidth = 2.dp.toPx(),
            alpha = alpha,
            cap = StrokeCap.Round
        )


        if (fiveSecondStep) {
            val countText = if (lineCount == 0) 60 else lineCount / 4
            val textSize = 24.sp.toPx()

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
        ChronometerBackground()
    }
}
