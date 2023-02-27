package com.plcoding.audiorecorder.playback

import java.io.File

interface IAudioPlayer {
    fun playFile(file: File)
    fun stop()
}