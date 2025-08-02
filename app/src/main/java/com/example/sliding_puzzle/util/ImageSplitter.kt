package com.example.sliding_puzzle.util

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

object ImageSplitter {
    fun split(bitmap: Bitmap, rows: Int = 4, cols: Int = 4): List<ImageBitmap> {
        val tileWidth = bitmap.width / cols
        val tileHeight = bitmap.height / rows

        if (tileWidth <= 0 || tileHeight <= 0) {
            throw IllegalArgumentException("Tile size is too small for selected difficulty")
        }

        val tiles = mutableListOf<ImageBitmap>()

        for (row in 0 until rows) {
            for (col in 0 until cols) {
                val x = col * tileWidth
                val y = row * tileHeight
                val tile = Bitmap.createBitmap(bitmap, x, y, tileWidth, tileHeight)
                tiles.add(tile.asImageBitmap())
            }
        }

        return tiles.dropLast(1)
    }
}