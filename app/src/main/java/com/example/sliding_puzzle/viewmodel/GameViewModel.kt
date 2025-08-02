package com.example.sliding_puzzle.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sliding_puzzle.data.BestTimeRepository
import com.example.sliding_puzzle.model.PuzzleTile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    val tiles = mutableStateListOf<PuzzleTile>()

    private val _timeElapsed = MutableStateFlow(0)
    val timeElapsed: StateFlow<Int> = _timeElapsed

    private val _bestTime = MutableStateFlow(Int.MAX_VALUE)
    val bestTime: StateFlow<Int> = _bestTime

    private val _moveCount = MutableStateFlow(0)
    val moveCount: StateFlow<Int> = _moveCount

    private val _hasWon = MutableStateFlow(false)
    val hasWon: StateFlow<Boolean> = _hasWon

    private lateinit var repository: BestTimeRepository
    fun init(context: Context) {
        repository = BestTimeRepository(context)
        viewModelScope.launch {
            _bestTime.value = repository.getBestTime()
        }
    }

    private var timerJob: Job? = null

    private var gridSize: Int = 4

    fun initializeTiles(tileBitmaps: List<ImageBitmap>, gridSize: Int) {
        this.gridSize = gridSize
        tiles.clear()
        val totalTiles = gridSize * gridSize
        val tilesWithEmpty = tileBitmaps.take(totalTiles - 1)

        tilesWithEmpty.forEachIndexed { i, bitmap ->
            tiles.add(
                PuzzleTile(
                    correctIndex = i,
                    currentIndex = i,
                    bitmap = bitmap,
                    isEmpty = false
                )
            )
        }

        tiles.add(
            PuzzleTile(
                correctIndex = totalTiles - 1,
                currentIndex = totalTiles - 1,
                bitmap = null,
                isEmpty = true
            )
        )
        shuffleTiles()
        startTimer()
    }

    private fun shuffleTiles() {
        val shuffled = tiles.shuffled()
        tiles.clear()
        shuffled.forEachIndexed { index, tile ->
            tiles.add(tile.copy(currentIndex = index))
        }
    }

    fun moveTile(clicked: PuzzleTile) {
        val empty = tiles.firstOrNull { it.isEmpty } ?: return

        if (isAdjacent(clicked.currentIndex, empty.currentIndex)) {
            val clickedIndex = clicked.currentIndex
            val emptyIndex = empty.currentIndex

            tiles.replaceAll { tile ->
                when (tile.currentIndex) {
                    clickedIndex -> tile.copy(currentIndex = emptyIndex)
                    emptyIndex -> tile.copy(currentIndex = clickedIndex)
                    else -> tile
                }
            }

            _moveCount.value++

            if (tiles.filter { !it.isEmpty }.all { it.currentIndex == it.correctIndex }) {
                _hasWon.value = true
                timerJob?.cancel()
            }
        }
    }

    private fun isAdjacent(i1: Int, i2: Int): Boolean {
        val row1 = i1 / gridSize
        val col1 = i1 % gridSize
        val row2 = i2 / gridSize
        val col2 = i2 % gridSize
        return (row1 == row2 && kotlin.math.abs(col1 - col2) == 1) ||
               (col1 == col2 && kotlin.math.abs(row1 - row2) == 1)
    }

    private fun startTimer() {
        timerJob?.cancel()
        _timeElapsed.value = 0
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                delay(1000)
                _timeElapsed.value++
            }
        }
    }

    fun restartGame() {
        viewModelScope.launch {
            if (_timeElapsed.value < _bestTime.value) {
                _bestTime.value = _timeElapsed.value
                repository.saveBestTime(_timeElapsed.value)
            }
        }
        tiles.shuffle()
        tiles.forEachIndexed { index, tile ->
            tiles[index] = tile.copy(currentIndex = index)
        }
        _moveCount.value = 0
        _hasWon.value = false
        startTimer()
    }
}
