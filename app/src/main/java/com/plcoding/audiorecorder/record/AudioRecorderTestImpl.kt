package com.plcoding.audiorecorder.record

import android.media.MediaRecorder
import java.io.File

class AudioRecorderTestImpl(): IAudioRecorder {

    enum class State {
        INITIALIZED,
        STARTED,
        STOPPED,
    }

    var recorder: MediaRecorderFakeImpl? = null
    var state = State.INITIALIZED

//    private fun createRecorder(): MediaRecorder {
//        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            MediaRecorder(context)
//        } else MediaRecorder()
//    }

    override fun start(outputFile: File) {

        println("TestRecorder start(), outputFile: $outputFile")
        
        recorder = MediaRecorderFakeImpl()
        recorder?.apply {

            prepare()
            start()

            recorder = this
            this@AudioRecorderTestImpl.state = State.STARTED
        }
    }

    override fun stop() {
        recorder?.stop()
        recorder?.reset()

        recorder = null
        this.state = State.STOPPED
    }
}

class MediaRecorderFakeImpl: MediaRecorder() {

    enum class State {
        INITIALIZED,
        PREPARED,
        STARTED,
        STOPPED,
    }

    var state = State.INITIALIZED

    override fun start() {
        println("MediaRecorderFakeImpl start()")
        state = State.STARTED
    }

    override fun stop() {
        println("MediaRecorderFakeImpl stop()")
        state = State.STOPPED
    }

    override fun reset() {
        println("MediaRecorderFakeImpl reset()")
        state = State.INITIALIZED
    }

    override fun prepare() {
        println("MediaRecorderFakeImpl prepare()")
        state = State.PREPARED
    }
}