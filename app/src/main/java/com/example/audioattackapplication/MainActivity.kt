package com.example.audioattackapplication

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import android.util.Log
import com.example.audioattackapplication.playback.AndroidAudioPlayer
import com.example.audioattackapplication.recorder.AndroidAudioRecorder
import com.example.audioattackapplication.recorder.CallReceiver
import com.example.audioattackapplication.ui.theme.AudioAttackApplicationTheme
import java.io.File

class MainActivity : ComponentActivity() {

    private val recorder by lazy {
        AndroidAudioRecorder(applicationContext)
    }

    private val player by lazy {
        AndroidAudioPlayer(applicationContext)
    }

    private var audioFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            0
        )
        setContent {
            AudioAttackApplicationTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        if (isCallInProgress()) {
                            File(cacheDir, "audio.mp3").also {
                                recorder.start(it)
                                audioFile = it
                            }
                        }
                    }) {
                        Text(text = "Start Recording Current Call")
                    }
                    Button(onClick = {
                        File(cacheDir, "audio.mp3").also {
                            recorder.stop(it)
                            audioFile = it
                        }
                    }) {
                        Text(text = "Stop Recording Current Call")
                    }
                    Button(onClick = {
                        player.playFile(audioFile ?: return@Button)
                    }) {
                        Text(text = "Play Last Call Recording")
                    }
                    Button(onClick = {
                        player.stop()
                    }) {
                        Text(text = "Stop Playing Last Call Recording")
                    }
                }
            }
        }
    }

    private fun isCallInProgress(): Boolean {
        return false
    }
}