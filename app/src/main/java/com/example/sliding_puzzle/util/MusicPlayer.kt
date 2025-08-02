package com.example.sliding_puzzle.util

import android.content.Context
import android.media.MediaPlayer

object MusicPlayer {
    private var mediaPlayer: MediaPlayer? = null

    fun start(context: Context, resId: Int) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, resId)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
        }
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}