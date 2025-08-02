package com.example.sliding_puzzle.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun ImageCarousel(
    imageList: List<Int>,
    selectedIndex: Int,
    onImageSelected: (Int) -> Unit
) {
    val listState = rememberLazyListState()

    LaunchedEffect(selectedIndex) {
        listState.animateScrollToItem(index = selectedIndex, scrollOffset = -100)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LazyRow(
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 100.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(imageList) { index, resId ->
                val isSelected = selectedIndex == index
                val animatedElevation by animateDpAsState(
                    targetValue = if (isSelected) 12.dp else 4.dp,
                    animationSpec = tween(300),
                    label = "elevation"
                )

                Card(
                    modifier = Modifier
                        .size(160.dp)
                        .clickable { onImageSelected(index) },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = animatedElevation),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = if (isSelected) {
                        BorderStroke(3.dp, Color(0xFF3B82F6))
                    } else null
                ) {
                    Image(
                        painter = painterResource(id = resId),
                        contentDescription = "Puzzle ${index + 1}",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            imageList.forEachIndexed { index, _ ->
                val isSelected = selectedIndex == index
                val animatedWidth by animateDpAsState(
                    targetValue = if (isSelected) 24.dp else 8.dp,
                    animationSpec = tween(300),
                    label = "indicatorWidth"
                )

                Box(
                    modifier = Modifier
                        .width(animatedWidth)
                        .height(8.dp)
                        .background(
                            if (isSelected) Color(0xFF3B82F6) else Color(0xFFCBD5E1),
                            RoundedCornerShape(4.dp)
                        )
                        .clickable { onImageSelected(index) }
                )
            }
        }
    }
}