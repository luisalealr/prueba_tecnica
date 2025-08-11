package com.example.prueba_tecnica.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prueba_tecnica.ui.theme.Prueba_tecnicaTheme

@Preview
@Composable
fun FilterDemand() {
    Prueba_tecnicaTheme(dynamicColor = false) {
        Box(modifier = Modifier.fillMaxWidth().background(Color.White)) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                color = Color.White,
                shadowElevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilterRow("Filtrar por ", Icons.Filled.FilterAlt, "filtrado")
                    FilterRow("Ordenar por ", Icons.Filled.FilterList, "ordenar")
                }

            }
            FloatingActionButton(
                onClick = { },
                shape = CircleShape,
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = 15.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    Icons.Filled.Add,
                    "Large floating action button",
                    Modifier.size(40.dp),
                    tint = Color.White
                )
            }
        }

    }
}

@Composable
fun FilterRow(text: String, icon: ImageVector, description: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = text, style = MaterialTheme.typography.titleSmall)
        Icon(imageVector = icon, contentDescription = description)
    }
}