package com.example.prueba_tecnica.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import com.example.prueba_tecnica.data.model.ResponseAPI
import com.example.prueba_tecnica.ui.formats.DescriptionLabel
import com.example.prueba_tecnica.ui.formats.TitleLabel
import com.example.prueba_tecnica.ui.formats.formatFecha
import com.example.prueba_tecnica.ui.theme.Prueba_tecnicaTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReportCard(report: ResponseAPI, onClick: () -> Unit) {

    val providerFirstName = report.reportFolioUserAssign
        ?.firstOrNull()
        ?.firstName
        ?: "Sin asignar"

    val providerLastName = report.reportFolioUserAssign
        ?.firstOrNull()
        ?.lastName
        ?: ""

    Prueba_tecnicaTheme(dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(480.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .padding(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 12.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        IdInfo(report.id, report.type)
                        Spacer(modifier = Modifier.size(10.dp))
                        TitleInfo(
                            report.name,
                            report.createTime,
                            report.priority.name,
                            report.status.description
                        )
                    }
                    AreaInfo(report.area.name, report.department.name, report.store.id)

                    PeopleInfo(
                        report.createdByUser.firstName,
                        report.createdByUser.lastName,
                        report.attendingByUser?.firstName,
                        report.attendingByUser?.lastName,
                        providerFirstName,
                        providerLastName
                    )
                    DetailButton(onClick)
                }
            }
        }
    }
}

@Composable
fun DetailButton(onClick: () -> Unit) {
    Button(
        onClick = { onClick() }, modifier = Modifier
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
fun Categories(priority: String?, status: String?) {
    Row {
        Text(
            text = priority ?: "No tiene prioridad asignada",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.secondary)
                .padding(horizontal = 10.dp, vertical = 4.dp),
            color = Color.White
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = status ?: "",
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
fun IdInfo(id: Int, type: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "# $id",
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray
        )
        Text(
            text = type,
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
fun AreaInfo(area: String, department: String, unity: Int) {
    Column(
        modifier = Modifier.height(70.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            TitleLabel("Área")
            DescriptionLabel(area)
        }
        Row {
            TitleLabel("Departamento")
            DescriptionLabel(department)
        }
        Row {
            TitleLabel("Unidad")
            DescriptionLabel("$unity")
        }
    }
}

@Composable
fun PeopleInfo(
    creatorFirstName: String,
    creatorLastName: String,
    solverFirstName: String?,
    solverLastName: String?,
    providerFirstName: String?,
    providerLastName: String?
) {
    Column(
        modifier = Modifier.height(70.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            TitleLabel("Creador")
            DescriptionLabel("$creatorFirstName $creatorLastName")
        }
        Row {
            TitleLabel("Proveedor")
            if (providerFirstName.isNullOrBlank() && providerLastName.isNullOrBlank()) {
                DescriptionLabel("No Asignado")
            } else {
                DescriptionLabel("$providerFirstName $providerLastName")
            }
        }
        Row {
            TitleLabel("Solucionador")
            if (solverFirstName.isNullOrBlank() && solverLastName.isNullOrBlank()) {
                DescriptionLabel("No Asignado")
            } else {
                DescriptionLabel("$solverFirstName $solverLastName")
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TitleInfo(name: String, createdAt: String, priority: String, status: String) {
    val date = formatFecha(createdAt)
    Text(
        text = name,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.surface,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
    )
    Spacer(modifier = Modifier.size(5.dp))
    Categories(priority, status)
    Spacer(modifier = Modifier.size(10.dp))
    Row {
        TitleLabel("Solicitada el")
        DescriptionLabel(date)
    }
}