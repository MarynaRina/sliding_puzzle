package com.example.sliding_puzzle.ui.screen

import android.graphics.BitmapFactory
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sliding_puzzle.R
import com.example.sliding_puzzle.model.PuzzleTile
import com.example.sliding_puzzle.ui.component.OriginalImageDialog
import com.example.sliding_puzzle.ui.component.VictoryDialog
import com.example.sliding_puzzle.util.ImageSplitter
import com.example.sliding_puzzle.viewmodel.GameViewModel

@Composable
private fun ControlsSection(
    onShowOriginal: () -> Unit,
    onRestart: () -> Unit
) {
    val customFont = FontFamily(Font(R.font.rubik_regular))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onShowOriginal,
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF10B981),
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 12.dp
                ),
                modifier = Modifier
                    .height(56.dp)
                    .width(160.dp)
                    .shadow(12.dp, RoundedCornerShape(50.dp))
            ) {
                Text(
                    text = "Original",
                    fontFamily = customFont,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Button(
                onClick = onRestart,
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
                    .width(160.dp)
                    .shadow(12.dp, RoundedCornerShape(50.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Restart",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Restart",
                    fontFamily = customFont,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun PuzzleGrid(
    tiles: List<PuzzleTile>,
    difficulty: Int,
    tileSize: Dp,
    tileSpacing: Dp,
    onTileClick: (PuzzleTile) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .shadow(16.dp, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(tileSpacing)
        ) {
            for (row in 0 until difficulty) {
                Row(horizontalArrangement = Arrangement.spacedBy(tileSpacing)) {
                    for (col in 0 until difficulty) {
                        val index = row * difficulty + col
                        val tile = tiles.find { it.currentIndex == index }

                        if (tile != null && !tile.isEmpty) {
                            key(tile.correctIndex) {
                                Card(
                                    modifier = Modifier
                                        .size(tileSize)
                                        .clickable { onTileClick(tile) },
                                    shape = RoundedCornerShape(
                                        when (difficulty) {
                                            3 -> 8.dp
                                            4 -> 6.dp
                                            else -> 4.dp
                                        }
                                    ),
                                    elevation = CardDefaults.cardElevation(
                                        defaultElevation = 4.dp,
                                        pressedElevation = 8.dp
                                    )
                                ) {
                                    Image(
                                        bitmap = tile.bitmap!!,
                                        contentDescription = "Tile ${tile.correctIndex}",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(
                                                RoundedCornerShape(
                                                    when (difficulty) {
                                                        3 -> 8.dp
                                                        4 -> 6.dp
                                                        else -> 4.dp
                                                    }
                                                )
                                            )
                                    )
                                }
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(tileSize)
                                    .background(
                                        Color(0xFFF1F5F9),
                                        RoundedCornerShape(
                                            when (difficulty) {
                                                3 -> 8.dp
                                                4 -> 6.dp
                                                else -> 4.dp
                                            }
                                        )
                                    )
                                    .border(
                                        1.dp,
                                        Color(0xFFCBD5E1),
                                        RoundedCornerShape(
                                            when (difficulty) {
                                                3 -> 8.dp
                                                4 -> 6.dp
                                                else -> 4.dp
                                            }
                                        )
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoCard(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 20.sp
) {
    val customFont = FontFamily(Font(R.font.rubik_regular))
    Card(
        modifier = modifier
            .padding(top = 8.dp)
            .shadow(8.dp, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.9f)
        )
    ) {
        Text(
            text = text,
            color = Color(0xFF475569),
            fontSize = fontSize,
            fontFamily = customFont,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp)
        )
    }
}

@Composable
private fun StatsSection(bestTime: Int, time: Int, moves: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (bestTime > 0) {
            InfoCard(text = "Best: ${formatTime(bestTime)}")
        }
        InfoCard(text = "Time: ${formatTime(time)}", fontSize = 24.sp)
        InfoCard(text = "Moves: $moves")
    }
}

fun formatTime(totalSeconds: Int): String {
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    return when {
        hours > 0 -> "%d:%02d:%02d".format(hours, minutes, seconds)
        minutes > 0 -> "%d:%02d".format(minutes, seconds)
        else -> "$seconds s"
    }
}

@Composable
private fun calculateTileSize(difficulty: Int): Dp {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    return when (difficulty) {
        3 -> {
            val padding = 48.dp + 32.dp + (2.dp * 2)
            val availableWidth = screenWidth - padding
            minOf(availableWidth / 3, 100.dp)
        }
        4 -> {
            val padding = 48.dp + 32.dp + (3.dp * 3)
            val availableWidth = screenWidth - padding
            minOf(availableWidth / 4, 75.dp)
        }
        5 -> {
            val padding = 48.dp + 32.dp + (2.dp * 4)
            val availableWidth = screenWidth - padding
            minOf(availableWidth / 5, 60.dp)
        }
        else -> 50.dp
    }
}

@Composable
fun GameScreen(imageResId: Int?, difficulty: Int = 4) {
    val context = LocalContext.current
    val viewModel: GameViewModel = viewModel()
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    LaunchedEffect(Unit) {
        viewModel.init(context)
    }
    val customFont = FontFamily(Font(R.font.rubik_regular))

    var showOriginalDialog by remember { mutableStateOf(false) }

    LaunchedEffect(imageResId) {
        imageResId?.let {
            val bitmap = BitmapFactory.decodeResource(context.resources, it)
            val bitmaps = ImageSplitter.split(bitmap, difficulty, difficulty)
            viewModel.initializeTiles(bitmaps, difficulty)
        }
    }

    val tiles = viewModel.tiles
    val bestTime by viewModel.bestTime.collectAsState()
    val time by viewModel.timeElapsed.collectAsState()
    val moves by viewModel.moveCount.collectAsState()
    val hasWon by viewModel.hasWon.collectAsState()

    val tileSize = calculateTileSize(difficulty)
    val tileSpacing = when (difficulty) {
        3 -> 4.dp
        4 -> 3.dp
        5 -> 2.dp
        else -> 2.dp
    }

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
                .padding(top = 64.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(onClick = { backDispatcher?.onBackPressed() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF475569),
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Sliding Puzzle",
                        color = Color(0xFF1E293B),
                        fontSize = 32.sp,
                        fontFamily = customFont,
                        fontWeight = FontWeight.Bold
                    )
                }

                StatsSection(bestTime = bestTime, time = time, moves = moves)
            }

            PuzzleGrid(
                tiles = tiles,
                difficulty = difficulty,
                tileSize = tileSize,
                tileSpacing = tileSpacing,
                onTileClick = { viewModel.moveTile(it) }
            )

            ControlsSection(
                onShowOriginal = { showOriginalDialog = true },
                onRestart = { viewModel.restartGame() }
            )
        }
    }

    if (showOriginalDialog && imageResId != null) {
        OriginalImageDialog(imageResId = imageResId, onDismiss = { showOriginalDialog = false })
    }

    if (hasWon) {
        VictoryDialog(
            time = time,
            moves = moves,
            onRestart = { viewModel.restartGame() }
        )
    }
}
