package com.example.firebaselogin.navigation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    cutoutRadius: Float = 40f
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
        ) {
            val width = size.width
            val height = size.height
            val cutoutDiameter = cutoutRadius * 2

            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo((width - cutoutDiameter) / 2, 0f)
                arcTo(
                    rect = androidx.compose.ui.geometry.Rect(
                        left = (width - cutoutDiameter) / 2,
                        top = -cutoutRadius,
                        right = (width + cutoutDiameter) / 2,
                        bottom = cutoutRadius
                    ),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = -180f,
                    forceMoveTo = false
                )
                lineTo(width, 0f)
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }

            drawPath(
                path = path,
                color = backgroundColor
            )
        }
    }
}

@Composable
fun BottomBarWithCutout(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    CustomBottomBar(modifier = modifier)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomBarWithCutout() {
    MaterialTheme {
        Scaffold(
            bottomBar = {
                BottomBarWithCutout {
                    Spacer(modifier = Modifier.weight(1f))
                    FloatingActionButton(
                        onClick = {},
                        modifier = Modifier.offset(y = (-28).dp) // Adjust offset to position the FAB in the cutout
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add"
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text(text = "Ahoj")
                }
            }
        )
    }
}