package com.franksap2.watch.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = GrayBlue500,
    primaryVariant = GrayBlue700,
    surface = Gray900,
    onPrimary = Color.White
)

private val LightColorPalette = lightColors(
    primary = GrayBlue500,
    primaryVariant = GrayBlue700,
    onPrimary = Color.White
)

@Composable
fun ChronometerComposeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}