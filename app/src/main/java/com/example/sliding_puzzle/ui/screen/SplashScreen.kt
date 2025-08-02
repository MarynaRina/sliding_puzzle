package com.example.sliding_puzzle.ui.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sliding_puzzle.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavHostController) {
    val logoScale = remember { Animatable(0.3f) }
    val logoAlpha = remember { Animatable(0f) }
    val logoRotation = remember { Animatable(-10f) }
    val titleAlpha = remember { Animatable(0f) }
    val titleOffsetY = remember { Animatable(30f) }
    val subtitleAlpha = remember { Animatable(0f) }
    val subtitleOffsetY = remember { Animatable(20f) }
    val loadingAlpha = remember { Animatable(0f) }
    val backgroundAlpha = remember { Animatable(0f) }

    val customFont = FontFamily(Font(R.font.rubik_regular))

    LaunchedEffect(true) {
        launch {
            backgroundAlpha.animateTo(1f, animationSpec = tween(500))
        }

        launch {
            delay(200)
            logoAlpha.animateTo(1f, animationSpec = tween(600))
        }
        launch {
            delay(300)
            logoScale.animateTo(1f, animationSpec = tween(800, easing = FastOutSlowInEasing))
        }
        launch {
            delay(400)
            logoRotation.animateTo(0f, animationSpec = tween(700, easing = FastOutSlowInEasing))
        }

        launch {
            delay(800)
            titleAlpha.animateTo(1f, animationSpec = tween(600))
        }
        launch {
            delay(800)
            titleOffsetY.animateTo(0f, animationSpec = tween(600, easing = FastOutSlowInEasing))
        }

        launch {
            delay(1200)
            subtitleAlpha.animateTo(1f, animationSpec = tween(500))
        }
        launch {
            delay(1200)
            subtitleOffsetY.animateTo(0f, animationSpec = tween(500, easing = FastOutSlowInEasing))
        }
        launch {
            delay(1600)
            loadingAlpha.animateTo(1f, animationSpec = tween(400))
        }

        kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main.immediate) {
            delay(3000)
            navController.navigate("select") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF8FAFC).copy(alpha = backgroundAlpha.value),
                        Color(0xFFE2E8F0).copy(alpha = backgroundAlpha.value),
                        Color(0xFFCBD5E1).copy(alpha = backgroundAlpha.value)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .size(120.dp)
                    .graphicsLayer {
                        scaleX = logoScale.value
                        scaleY = logoScale.value
                        alpha = logoAlpha.value
                        rotationZ = logoRotation.value
                    }
                    .shadow(16.dp, RoundedCornerShape(30.dp)),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF3B82F6)
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_puzzle),
                        contentDescription = "Puzzle Logo",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Sliding Puzzle",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = customFont,
                color = Color(0xFF1E293B),
                modifier = Modifier.graphicsLayer {
                    alpha = titleAlpha.value
                    translationY = titleOffsetY.value
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Challenge your mind with beautiful puzzles",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = customFont,
                color = Color(0xFF64748B),
                modifier = Modifier.graphicsLayer {
                    alpha = subtitleAlpha.value
                    translationY = subtitleOffsetY.value
                }
            )

            Spacer(modifier = Modifier.height(60.dp))

            Box(
                modifier = Modifier.graphicsLayer {
                    alpha = loadingAlpha.value
                }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = Color(0xFF3B82F6),
                        strokeWidth = 3.dp,
                        modifier = Modifier.size(32.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Loading...",
                        fontSize = 14.sp,
                        fontFamily = customFont,
                        color = Color(0xFF64748B),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                text = "Version 1.0",
                fontSize = 12.sp,
                fontFamily = customFont,
                color = Color(0xFF94A3B8),
                modifier = Modifier.graphicsLayer {
                    alpha = loadingAlpha.value
                }
            )
        }
    }
}