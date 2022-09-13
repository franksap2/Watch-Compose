package com.franksap2.chronometer.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franksap2.chronometer.R
import com.franksap2.chronometer.ui.components.MinutesDial
import com.franksap2.chronometer.ui.components.SecondsDial
import com.franksap2.chronometer.ui.components.Watch
import com.franksap2.chronometer.ui.state.rememberWatchState
import com.franksap2.chronometer.ui.theme.ChronometerComposeTheme

private val watchPadding = 20.dp
private val watchBorder = 10.dp
private val watchContentPadding = 13.dp

private val internalDialSize = 80.dp
private val internalDialPadding = 65.dp

@Composable
fun MainScreen() {

    val chronometerState = rememberWatchState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Box(
            modifier = Modifier
                .padding(watchPadding)
                .border(watchBorder, MaterialTheme.colors.primaryVariant, CircleShape)
                .background(MaterialTheme.colors.primary, CircleShape)
                .padding(watchContentPadding)
                .aspectRatio(1f)
        ) {

            Column(modifier = Modifier.align(Alignment.Center), verticalArrangement = Arrangement.spacedBy(16.dp)) {

                MinutesDial(
                    modifier = Modifier
                        .padding(top = internalDialPadding)
                        .size(internalDialSize),
                    progressProvider = { chronometerState.currentChronometer }
                )
                SecondsDial(
                    modifier = Modifier
                        .padding(bottom = internalDialPadding)
                        .size(internalDialSize),
                    progressProvider = { chronometerState.currentChronometer }
                )
            }

            Watch(watchState = chronometerState)
        }

        Box {
            PlayButton(
                modifier = Modifier.align(Alignment.Center),
                onCheckStateChange = { checked ->
                    if (checked)
                        chronometerState.playChronometer()
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
        modifier = modifier.background(MaterialTheme.colors.primary, CircleShape),
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