
package com.example.sliding_puzzle.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sliding_puzzle.R
import com.example.sliding_puzzle.ui.component.DifficultySelector
import com.example.sliding_puzzle.ui.component.ImageCarousel
import com.example.sliding_puzzle.ui.component.MusicToggleButton
import com.example.sliding_puzzle.util.MusicPlayer

@Composable
fun ImageSelectScreen(navController: NavController) {
    val context = LocalContext.current
    val customFont = FontFamily(Font(R.font.rubik_regular))

    val imageList = listOf(
        R.drawable.puzzle1,
        R.drawable.puzzle2,
        R.drawable.puzzle3,
        R.drawable.puzzle4,
        R.drawable.puzzle5,
    )

    var selectedIndex by remember { mutableIntStateOf(0) }
    var selectedDifficulty by remember { mutableIntStateOf(4) }
    var isMusicPlaying by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF8FAFC),
                        Color(0xFFE2E8F0),
                        Color(0xFFCBD5E1)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 32.dp)
            ) {
                Text(
                    text = "Choose Your Puzzle",
                    color = Color(0xFF1E293B),
                    fontSize = 32.sp,
                    fontFamily = customFont,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Select an image to start playing",
                    color = Color(0xFF64748B),
                    fontSize = 16.sp,
                    fontFamily = customFont,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            ImageCarousel(
                imageList = imageList,
                selectedIndex = selectedIndex,
                onImageSelected = { selectedIndex = it }
            )

            DifficultySelector(
                selectedDifficulty = selectedDifficulty,
                onDifficultySelected = { selectedDifficulty = it }
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MusicToggleButton(
                    isMusicPlaying = isMusicPlaying,
                    onToggle = {
                        isMusicPlaying = !isMusicPlaying
                        if (isMusicPlaying) {
                            MusicPlayer.start(context, R.raw.game_bg)
                        } else {
                            MusicPlayer.stop()
                        }
                    }
                )

                Button(
                    onClick = {
                        val selectedImageId = imageList[selectedIndex]
                        navController.navigate("game/$selectedImageId/$selectedDifficulty")
                    },
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3B82F6),
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 12.dp
                    ),
                    modifier = Modifier
                        .height(56.dp)
                        .width(200.dp)
                        .shadow(12.dp, RoundedCornerShape(50.dp))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Start",
                            tint = Color.White
                        )
                        Text(
                            text = "Start Game",
                            fontFamily = customFont,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
