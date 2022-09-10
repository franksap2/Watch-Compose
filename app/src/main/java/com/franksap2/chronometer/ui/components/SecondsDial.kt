package com.franksap2.chronometer.ui.components

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.franksap2.chronometer.ui.theme.ChronometerComposeTheme


@Composable
fun SecondsDial() {

    Chronometer(
        resolution = 1,
        textSize = 24.sp,
        lineSize = 10.dp,
        lineMaxSize = 20.dp,
        lineWidth = 2.dp,
    )

}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ChronometerPreview() {
    ChronometerComposeTheme {
        SecondsDial()
    }
}