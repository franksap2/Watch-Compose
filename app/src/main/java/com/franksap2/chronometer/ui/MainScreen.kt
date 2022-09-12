package com.franksap2.chronometer.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franksap2.chronometer.R
import com.franksap2.chronometer.ui.components.MinutesDial
import com.franksap2.chronometer.ui.components.SecondsDial
import com.franksap2.chronometer.ui.components.Watch
import com.franksap2.chronometer.ui.state.rememberWatchState
import com.franksap2.chronometer.ui.theme.Amber700
import com.franksap2.chronometer.ui.theme.ChronometerComposeTheme

@Composable
fun MainScreen() {

    val chronometerState = rememberWatchState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Box(
            modifier = Modifier
                .padding(20.dp)
                .border(3.dp, Amber700, CircleShape)
                .shadow(4.dp, CircleShape)
                .background(MaterialTheme.colors.surface, CircleShape)
                .aspectRatio(1f)
        ) {
            MinutesDial(
                modifier = Modifier
                    .padding(top = 65.dp)
                    .size(80.dp)
                    .align(Alignment.TopCenter),
                progressProvider = { chronometerState.currentChronometer }
            )
            SecondsDial(
                modifier = Modifier
                    .padding(bottom = 65.dp)
                    .align(Alignment.BottomCenter)
                    .size(80.dp),
                progressProvider = { chronometerState.currentChronometer }
            )

            Watch(progressProvider = { chronometerState.currentTime })
        }

        Box {
            PlayButton(
                modifier = Modifier.align(Alignment.Center),
                onCheckStateChange = { checked ->
                    if (checked)
                        chronometerState.play()
                    else
                        chronometerState.pause()
                }
            )

        }
    }
}

@Composable
private fun PlayButton(modifier: Modifier = Modifier, onCheckStateChange: (Boolean) -> Unit) {

    var isChecked by remember { mutableStateOf(false) }

    val icon = if (isChecked) R.drawable.ic_pause else R.drawable.ic_play

    IconToggleButton(
        modifier = modifier
            .shadow(4.dp, CircleShape)
            .background(Amber700, CircleShape),
        checked = isChecked,
        onCheckedChange = {
            onCheckStateChange(it)
            isChecked = it
        }
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = null, tint = MaterialTheme.colors.onPrimary)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenPreview() {
    ChronometerComposeTheme {
        MainScreen()
    }
}