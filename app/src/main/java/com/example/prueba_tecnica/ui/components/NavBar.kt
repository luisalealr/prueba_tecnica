package com.example.prueba_tecnica.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.zIndex
import com.example.prueba_tecnica.ui.theme.Prueba_tecnicaTheme

@SuppressLint("ConfigurationScreenWidthHeight")
@Preview
@Composable
fun NavBar() {
    val items = listOf("Principal", "Agenda", "Tiendas", "Folios")
    val icons = listOf(
        Icons.Default.CheckCircle,
        Icons.Default.CalendarMonth,
        Icons.Default.Storefront,
        Icons.Default.BugReport
    )
    val selectedIndex = remember { mutableIntStateOf(0) }

    val circleDiameter = 60.dp
    val circleRadius = circleDiameter / 2
    val circleGap = 8.dp

    val density = LocalDensity.current
    val screenWidthPx = with(density) { LocalConfiguration.current.screenWidthDp.dp.toPx() }
    val offset = screenWidthPx * 0.87f

    Prueba_tecnicaTheme(dynamicColor = false) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp),
                color = Color.White,
                shadowElevation = 10.dp,
                shape = ShapeNavBar(offset, circleRadius, circleGap),
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items.forEachIndexed { index, item ->
                        if (index == 3) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.weight(1f)
                            ) {
                                Spacer(modifier = Modifier.height(circleRadius))
                                Text(text = item, color = Color(0xFF3b87d9))
                            }
                        } else {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { selectedIndex.intValue = index }
                            ) {
                                Icon(
                                    icons[index],
                                    contentDescription = item,
                                    tint = Color(0xFF3b87d9),
                                    modifier = Modifier.size(35.dp)
                                )
                                Text(text = item, color = Color(0xFF3b87d9))
                            }
                        }
                    }
                }
            }
            IconButton(
                onClick = { selectedIndex.intValue = 3 },
                modifier = Modifier
                    .size(circleDiameter)
                    .offset {
                        IntOffset(
                            x = (offset - circleRadius.toPx()).toInt(),
                            y = (-circleRadius.toPx()).toInt()
                        )
                    }
                    .zIndex(1f)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF03DAC5), Color(0xFF6200EE))
                        ),
                        shape = RoundedCornerShape(50.dp)
                    )
            ) {
                Icon(icons[3], contentDescription = "FAB", tint = Color.White)
            }
        }
    }
}

private class ShapeNavBar(
    private val cutoutCenterX: Float,
    private val circleRadius: Dp,
    private val circleGap: Dp = 5.dp,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(createPath(size, density))
    }

    //Dibujar el navBar
    private fun createPath(size: Size, density: Density): Path {
        val cutoutRadius = with(density) { (circleRadius + circleGap).toPx() }

        return Path().apply {
            moveTo(0f, size.height)
            lineTo(0f, 0f)
            lineTo(cutoutCenterX - cutoutRadius, 0f)

            cubicTo(
                x1 = cutoutCenterX - cutoutRadius,
                y1 = 0f,
                x2 = cutoutCenterX - cutoutRadius,
                y2 = cutoutRadius,
                x3 = cutoutCenterX,
                y3 = cutoutRadius
            )
            cubicTo(
                x1 = cutoutCenterX + cutoutRadius,
                y1 = cutoutRadius,
                x2 = cutoutCenterX + cutoutRadius,
                y2 = 0f,
                x3 = cutoutCenterX + cutoutRadius,
                y3 = 0f
            )

            lineTo(size.width, 0f)
            lineTo(size.width, size.height)
            close()
        }
    }
}
