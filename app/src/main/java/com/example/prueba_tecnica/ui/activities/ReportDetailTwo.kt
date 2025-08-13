package com.example.prueba_tecnica.ui.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ReplayCircleFilled
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.prueba_tecnica.data.model.OptionIcon
import com.example.prueba_tecnica.data.model.ResponseAPI
import com.example.prueba_tecnica.ui.components.TopTogether
import com.example.prueba_tecnica.ui.formats.LabelValue
import com.example.prueba_tecnica.ui.formats.SectionHeader
import com.example.prueba_tecnica.ui.formats.TitleLabel
import com.example.prueba_tecnica.ui.theme.Prueba_tecnicaTheme
import com.example.prueba_tecnica.ui.viewmodel.ResponseViewModel

@Composable
fun ReportDetailTwo(navController: NavController, responseViewModel: ResponseViewModel) {

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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()
                ) {
                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        TopTogether(report, navController)
                        DetailInfoTwo(report)
                    }
                }
            }
        }
    }
}

@Composable
fun DetailInfoTwo(report: ResponseAPI?) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(22.dp)
    ){
        Column {
            IconsRow()
            Spacer(modifier = Modifier.height(16.dp))
            InfoTable(report)
            Spacer(modifier = Modifier.height(16.dp))
            ContentInfo(report)
        }
    }
}

@Composable
fun IconsRow() {
    Prueba_tecnicaTheme(dynamicColor = false) {
        val optionList = listOf(
            OptionIcon(Icons.Filled.Email, "email", MaterialTheme.colorScheme.primary),
            OptionIcon(Icons.Filled.Phone, "phone", MaterialTheme.colorScheme.primary),
            OptionIcon(
                Icons.Filled.ReplayCircleFilled,
                "refresh",
                MaterialTheme.colorScheme.secondary
            )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    optionList.forEachIndexed { index, item ->
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(item.color)
                                .padding(10.dp)
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.content,
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        if (index != optionList.lastIndex) {
                            Spacer(modifier = Modifier.width(40.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoTable(report: ResponseAPI?) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Creado por ${report?.createdByUser?.firstName} ${report?.createdByUser?.lastName}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.surface,
                textAlign = TextAlign.Center
            )
            DayHour("05/11/2024")
            DayHour( "11:11 AM")
        }
        Divider(
            modifier = Modifier
                .height(110.dp)
                .width(1.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            report?.reportFolioUserAssign?.size?.let {
                if(it > 0) {
                    Text(
                        text = "Aprobado por ${report.reportFolioUserAssign[0].firstName} ${report.reportFolioUserAssign[0].lastName}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.surface,
                        textAlign = TextAlign.Center
                    )
                    DayHour("05/11/2024")
                    DayHour( "11:11 AM")
                }else{
                    Text("No ha sido revisado")
                }
            }

        }
    }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Divider()
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(vertical = 5.dp, horizontal = 10.dp)
        ) {
            Text(
                text = "46 minutos",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun DayHour(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.surface
    )
}

@Composable
fun ContentInfo(report: ResponseAPI?) {
    Column {
        SectionHeader("Involucrados")
        LabelValue("Jefe directo", report?.department?.userManage?.firstName + " " + report?.department?.userManage?.lastName)
        Text(
            text = "Usuarios Asignados",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.surface
        )
        report?.reportFolioUserAssign?.size?.let {
            if(it > 0) {
                Row {
                    report.reportFolioUserAssign.forEach { userAssign ->
                        Text(
                            text = "${report.reportFolioUserAssign[0].firstName} ${report.reportFolioUserAssign[0].lastName} ",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.surface,
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }else{
                Text(
                    text = "No hay usuarios asignados",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.surface,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("Zona de gestión")
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            LabelValue("Unidad-ID", "123-Punto fijo", modifier = Modifier.weight(1f))
            LabelValue("Área", report?.area?.name, modifier = Modifier.weight(1f))
        }
        LabelValue("Departamento", report?.department?.name)

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("Categorización")
        LabelValue("Acabados", "Pintura, pulido de pisos")
        LabelValue("Electricidad", "Instalación")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("Cuestionario")

        if(report?.detailCreation != null){
            LabelValue("Nombre", report.detailCreation.questionnaire)
            LabelValue("Pregunta", report.detailCreation.question)
            RadioButtom()
        }else{
            Text("No ha realizado el cuestionario")
        }

    }
}

@Composable
fun RadioButtom(modifier: Modifier = Modifier){
    val radioOptions = listOf("1", "2", "3", "4", "5")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    Row (modifier.selectableGroup().fillMaxWidth()){
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null // null recommended for accessibility with screen readers
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}