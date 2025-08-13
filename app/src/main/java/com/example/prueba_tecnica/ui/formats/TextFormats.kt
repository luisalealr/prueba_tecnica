package com.example.prueba_tecnica.ui.formats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun TitleLabel(title: String) {
    Text(
        text = "$title: ",
        style = MaterialTheme.typography.bodyMedium,
        color = Color.Gray
    )
}

@Composable
fun DescriptionLabel(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.bodySmall,
        color = Color.Gray,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun SectionHeader(text: String) {
    androidx.compose.material.Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 8.dp),
        color = MaterialTheme.colorScheme.surface
    )
}

@Composable
fun LabelValue(label: String, value: String?, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(bottom = 8.dp)) {
        androidx.compose.material.Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.surface
        )
        if (value != null) {
            androidx.compose.material.Text(
                text = value,
                color = MaterialTheme.colorScheme.surface,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}