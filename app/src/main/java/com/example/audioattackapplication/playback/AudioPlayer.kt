package com.example.audioattackapplication.playback

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
}