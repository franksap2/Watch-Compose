package com.franksap2.watch.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.franksap2.watch.ui.theme.ChronometerComposeTheme
import com.franksap2.watch.ui.utils.MAX_ROTATION
import com.franksap2.watch.ui.utils.MINUTE_MILLI

@Composable
fun SecondsDial(progressProvider: () -> Long, modifier: Modifier = Modifier) {
    Chronometer(
        modifier = modifier,
        resolution = 2,
        textSize = 12.sp,
        lineSize = 4.dp,
        lineMaxSize = 6.dp,
        lineWidth = 1.dp,
        progressProvider = { MAX_ROTATION * (progressProvider() / MINUTE_MILLI) },
        dialScrewSize = 3.dp,
        centerDial = true,
        dialWidth = 2.dp,
        faceType = FaceType.SECONDS
    )
}


@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun ChronometerPreview() {
    ChronometerComposeTheme {
        SecondsDial(progressProvider = { 30_000L })
    }
}