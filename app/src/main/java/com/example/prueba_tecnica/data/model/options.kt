package com.example.prueba_tecnica.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class OptionItem(
    val title: String,
    val count: Int,
    val icon: ImageVector
)

data class OptionIcon(
    val icon: ImageVector,
    val content: String,
    val color: Color
)
