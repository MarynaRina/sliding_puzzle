package com.example.sliding_puzzle.model

import androidx.compose.ui.graphics.ImageBitmap

data class PuzzleTile(
    val correctIndex: Int,
    val currentIndex: Int,
    val bitmap: ImageBitmap?,
    val isEmpty: Boolean = false
)