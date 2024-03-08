package com.akighan.countdowntimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.akighan.countdowntimer.ui.theme.CountDownTimerTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountDownTimerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BasicCountDownTimerWithPause()
                }
            }
        }
    }
}

@Composable
fun BasicCountDownTimerWithPause() {
    var timeLeft by remember { mutableIntStateOf(60) }
    var isPaused by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = timeLeft, key2 = isPaused) {
        while (timeLeft > 0 && !isPaused) {
            delay(1000L)
            timeLeft--
        }
    }

    fun resetTimer() {
        timeLeft = 60
        isPaused = false
    }

    Column {
        Text(text = "Time left: $timeLeft")
        Button(onClick = { isPaused = !isPaused }) {
            Text(text = if (isPaused) "Resume" else "Pause")
        }
        Button(onClick = { resetTimer() }) {
            Text(text = "Reset")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    CountDownTimerTheme {
        BasicCountDownTimerWithPause()
    }
}