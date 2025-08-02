package com.example.sliding_puzzle.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sliding_puzzle.R

@Composable
fun DifficultySelector(
    selectedDifficulty: Int,
    onDifficultySelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val customFont = FontFamily(Font(R.font.rubik_regular))
    val difficulties = listOf(3, 4, 5)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = "Difficulty",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1E293B),
            fontFamily = customFont,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            difficulties.forEach { difficulty ->
                val isSelected = selectedDifficulty == difficulty
                Button(
                    onClick = { onDifficultySelected(difficulty) },
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) Color(0xFF3B82F6) else Color(0xFF94A3B8),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.height(40.dp)
                ) {
                    Text(
                        text = "${difficulty}x$difficulty",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = customFont
                    )
                }
            }
        }
    }
}