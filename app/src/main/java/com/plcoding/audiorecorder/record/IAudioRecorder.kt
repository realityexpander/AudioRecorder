package com.plcoding.audiorecorder.record

import java.io.File

interface IAudioRecorder {
    fun start(outputFile: File)
    fun stop()
}