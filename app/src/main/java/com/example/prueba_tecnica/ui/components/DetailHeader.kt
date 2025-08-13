package com.example.prueba_tecnica.ui.components

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.prueba_tecnica.data.model.ResponseAPI
import com.example.prueba_tecnica.ui.theme.Prueba_tecnicaTheme

@Composable
fun TopTogether(report: ResponseAPI?, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
    ) {
        Column {
            DetailHeader(navController)
            DetailReportContainer(report, navController, "Ver más")

        }
    }
}

@Composable
fun DetailHeader(navController: NavController) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .height(50.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "cuenta",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp),
                )
            }
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "cuenta",
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .background(MaterialTheme.colorScheme.secondary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "3+",
                        color = Color.White,
                        fontSize = 10.sp,
                        lineHeight = 10.sp
                    )
                }
            }
        }
    }
}


@Composable
fun DetailReportContainer(
    report: ResponseAPI?,
    navController: NavController,
    text: String
) {
    Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.onPrimary
                    )
                )
            )
            .fillMaxWidth()
            .height(200.dp)
            .padding(22.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Priority(report?.priority?.name ?: "", report?.status?.description ?: "")
            Text(
                text = "#${report?.id} - ${report?.name}",
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
            )
            ViewMore(report, navController, text)
        }
    }


}

@Composable
fun ViewMore(report: ResponseAPI?, navController: NavController, text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = report?.type ?: "",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 15.dp, vertical = 8.dp),
            color = Color.White
        )
        Box(
            modifier = Modifier
                .clickable {
                    if (text == "Ver más") {
                        navController.navigate("reportDetailTwo")
                    } else {
                        navController.popBackStack()
                    }
                },
        ) {
            Row {
                Text(text, color = Color.White)
                Icon(
                    imageVector = Icons.Filled.KeyboardDoubleArrowRight,
                    contentDescription = "Ver mas",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun Priority(priority: String?, report: String) {
    Row {
        if (priority != null) {
            Text(
                text = priority,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(horizontal = 15.dp, vertical = 8.dp),
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = report,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(horizontal = 15.dp, vertical = 8.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun DaysPassed(report: ResponseAPI?) {
    Prueba_tecnicaTheme(dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary)
                .padding(horizontal = 22.dp, vertical = 10.dp)
        ) {
            Text(
                text = "15 Días Transcurridos",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }

}