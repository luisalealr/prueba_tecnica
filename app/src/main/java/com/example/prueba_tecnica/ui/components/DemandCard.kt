package com.example.prueba_tecnica.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prueba_tecnica.ui.theme.Prueba_tecnicaTheme

@Preview
@Composable
fun DemandCard() {
    Prueba_tecnicaTheme(dynamicColor = false) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(12.dp))
                .padding(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    IdInfo()
                    Spacer(modifier = Modifier.size(10.dp))
                    TitleInfo()
                }
                AreaInfo()
                PeopleInfo()
                DetailButton()
            }
        }
    }
}

@Composable
fun DetailButton() {
    Button(
        onClick = {}, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = "Ver Detalle",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun Categories() {
    Row {
        Text(
            text = "Alta",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.secondary)
                .padding(horizontal = 10.dp, vertical = 4.dp),
            color = Color.White
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = "En espera de solución",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 10.dp, vertical = 4.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun IdInfo() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "# 123456789",
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray
        )
        Text(
            text = "Correctivo",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            color = Color.White
        )
    }
}

@Composable
fun AreaInfo() {
    Column(
        modifier = Modifier.height(70.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Text(
                text = "Área: ",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "Finanzas",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
        Row {
            Text(
                text = "Departamento: ",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "Contabilidad",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
        Row {
            Text(
                text = "Unidad: ",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "Sample 123",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun PeopleInfo() {
    Column(
        modifier = Modifier.height(70.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Text(
                text = "Creador: ",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "Juan Francisco Jimenez",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Row {
            Text(
                text = "Proveedor: ",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "Proveedor ABC",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
        Row {
            Text(
                text = "Solucionador: ",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "Juan Ramirez",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun TitleInfo() {
    Text(
        text = "Mantenimiento de A/A de oficina principal",
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.surface,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
    )
    Spacer(modifier = Modifier.size(5.dp))
    Categories()
    Spacer(modifier = Modifier.size(10.dp))
    Row {
        Text(
            text = "Solicitado el:",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Text(
            text = "DD/MM/YYYY HH:MM",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}