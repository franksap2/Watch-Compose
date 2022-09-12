package com.franksap2.chronometer.ui.components

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.franksap2.chronometer.ui.theme.ChronometerComposeTheme

private const val HALF_HOUR = 60_000 * 30f

@Composable
fun MinutesDial(modifier: Modifier = Modifier, progressProvider: () -> Long) {

    Chronometer(
        modifier = modifier,
        resolution = 2,
        textSize = 12.sp,
        lineSize = 4.dp,
        lineMaxSize = 6.dp,
        lineWidth = 1.dp,
        progressProvider = { 360f * (progressProvider() / HALF_HOUR) },
        dialScrewSize = 3.dp,
        centerDial = true,
        dialWidth = 2.dp
    )

}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ChronometerPreview() {
    ChronometerComposeTheme {
        MinutesDial { 60_000 }
    }
}