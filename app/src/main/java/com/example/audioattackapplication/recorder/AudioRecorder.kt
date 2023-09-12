package com.example.audioattackapplication.recorder

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)

    fun stop(outputFile: File)
}