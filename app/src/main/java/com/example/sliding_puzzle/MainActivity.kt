package com.example.sliding_puzzle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.sliding_puzzle.navigation.AppNavGraph
import com.example.sliding_puzzle.ui.theme.Sliding_PuzzleTheme
import com.example.sliding_puzzle.util.MusicPlayer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sliding_PuzzleTheme {
                val navController = rememberNavController()
                MusicPlayer.start(this, R.raw.game_bg)
                AppNavGraph(navController = navController)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        MusicPlayer.stop()
    }
}