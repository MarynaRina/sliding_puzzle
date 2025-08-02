package com.example.sliding_puzzle.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MusicToggleButton(
    isMusicPlaying: Boolean,
    onToggle: () -> Unit
) {
    OutlinedIconButton(
        onClick = onToggle,
        modifier = Modifier
            .size(56.dp)
            .background(
                if (isMusicPlaying) Color(0xFF3B82F6).copy(alpha = 0.1f) else Color.Transparent,
                CircleShape
            ),
        border = BorderStroke(
            width = 2.dp,
            color = if (isMusicPlaying) Color(0xFF3B82F6) else Color(0xFFE2E8F0)
        )
    ) {
        Icon(
            imageVector = if (isMusicPlaying) Icons.AutoMirrored.Filled.VolumeUp else Icons.AutoMirrored.Filled.VolumeOff,
            contentDescription = null,
            tint = if (isMusicPlaying) Color(0xFF3B82F6) else Color(0xFF64748B),
            modifier = Modifier.size(24.dp)
        )
    }
}