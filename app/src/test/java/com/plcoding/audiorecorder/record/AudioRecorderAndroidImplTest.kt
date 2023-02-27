package com.plcoding.audiorecorder.record

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.File

class AudioRecorderAndroidImplTest {

    var audioRecorder: AudioRecorderTestImpl? = null
    lateinit var outputFile: File

    @Before
    fun setUp() {
        audioRecorder = AudioRecorderTestImpl()
        outputFile = File("testFile")

        assertNotNull(audioRecorder)
        assertEnumEquals(
            AudioRecorderTestImpl.State.INITIALIZED,
            audioRecorder?.state
        )
    }

    @Test
    fun start() {
        // Arrange

        // Act
        audioRecorder?.start(outputFile)

        // Assert
        assertNotNull(audioRecorder?.recorder)
        assertEnumEquals(
            MediaRecorderFakeImpl.State.STARTED,
            audioRecorder?.recorder?.state
        )
        assertEnumEquals(
            AudioRecorderTestImpl.State.STARTED,
            audioRecorder?.state
        )
    }

    @Test
    fun stop() {
        // Arrange
        audioRecorder?.start(outputFile)

        // Act
        audioRecorder?.stop()

        // Assert
        assertNull(audioRecorder?.recorder)
        assertEnumEquals(
            AudioRecorderTestImpl.State.STOPPED,
            audioRecorder?.state
        )
    }

    fun <T : Enum<T>> assertEnumEquals(expected: T, actual: T?) {
        assertEquals(expected, actual)
    }
}