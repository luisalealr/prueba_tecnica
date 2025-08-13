package com.example.prueba_tecnica.ui.activities

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.prueba_tecnica.data.helper.TokenManager
import com.example.prueba_tecnica.data.model.ConfigActionPlanReportFolioEvidenceType
import com.example.prueba_tecnica.data.model.OptionItem
import com.example.prueba_tecnica.data.model.ResponseAPI
import com.example.prueba_tecnica.ui.components.DaysPassed
import com.example.prueba_tecnica.ui.components.TopTogether
import com.example.prueba_tecnica.ui.theme.Prueba_tecnicaTheme
import com.example.prueba_tecnica.ui.viewmodel.ResponseViewModel

@Composable
fun ReportDetail(reportId: Int, navController: NavController, responseViewModel: ResponseViewModel) {

    val context = LocalContext.current
    var token by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(true) {
        token = TokenManager.getToken(context)
        token?.let {
            responseViewModel.getReport(reportId, it)
        }
    }

    val report by responseViewModel.selectedReport
    val error by responseViewModel.errorMessage


    Prueba_tecnicaTheme(dynamicColor = false) {
        when {
            error.isNotEmpty() -> {
                Text("Error: $error", color = Color.Red)
            }
            report == null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            else -> {
                val comentarios = report?.configReportFolio?.configActionPlanReportFolioEvidenceTypes
                    ?.filter { it.evidenceType.key == "comment" || it.evidenceType.key == "comment_approve" }
                    ?: emptyList()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()
                ) {
                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        TopTogether(report, navController)
                        DaysPassed(report)
                        DetailInfo(report, comentarios)
                    }
                }
            }
        }
    }
}

@Composable
fun DetailInfo(report: ResponseAPI?, comentarios: List<ConfigActionPlanReportFolioEvidenceType>?) {
    val optionList = listOf(
        OptionItem("Evidencias", 0, Icons.Filled.AttachFile),
        OptionItem("Opciones Avanzadas", 0, Icons.AutoMirrored.Filled.ArrowForward),
        OptionItem("Informe de Folio", 0, Icons.Outlined.RemoveRedEye),
        OptionItem("Comentarios", comentarios?.size ?: 0, Icons.AutoMirrored.Filled.ArrowForward)
    )

    Prueba_tecnicaTheme(dynamicColor = false) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(22.dp)
        ) {
            Column {
                Column {
                    Text(
                        text = "Descripción",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.surface
                    )
                    Text(
                        text = report?.description ?: "",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.surface
                    )
                }
                Spacer(modifier = Modifier.size(15.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    optionList.forEach { item->
                        OptionCard(
                            title = item.title,
                            count = item.count,
                            icon = item.icon
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
                Spacer(modifier = Modifier.size(15.dp))
                Historial(report?.department?.userManage?.firstName ?: "",
                    report?.department?.userManage?.lastName ?: ""
                )
            }
        }
    }
}


@Composable
fun OptionCard(
    title: String,
    count: Int,
    icon: ImageVector,
) {
    Prueba_tecnicaTheme(dynamicColor = false) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(containerColor = Color.White)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp, horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.surface
                    )
                    if (count > 0) {
                        Spacer(modifier = Modifier.size(12.dp))
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.Red)
                                .height(24.dp)
                                .width(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$count",
                                color = Color.White
                            )
                        }
                    }
                }
                Icon(
                    imageVector = icon,
                    contentDescription = "icon",
                    tint = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

@Composable
fun Historial(firstName: String, lastName: String) {
    Column {
        Text(
            text = "Historial",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.surface
        )
        Divider()
        Spacer(modifier = Modifier.size(15.dp))
        RequestQuote(firstName, lastName)
    }
}

@Composable
fun RequestQuote(firstName: String, lastName: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 15.dp),
        ) {
            Text(
                text = "Pedir Cotización",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = "Proveedor: $firstName $lastName",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.surface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(10.dp))
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.tertiary
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary)
            ) {
                Text(
                    text = "Pedir Cotización",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            Spacer(modifier = Modifier.size(5.dp))
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
            ) {
                Text(
                    text = "Pedir Cotización",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }

        }
    }
}