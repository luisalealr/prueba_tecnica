package com.example.prueba_tecnica.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prueba_tecnica.R
import com.example.prueba_tecnica.ui.theme.Prueba_tecnicaTheme

@Preview
@Composable
fun TopBar() {
    Prueba_tecnicaTheme(dynamicColor = false) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
        ){
            Column {
                Header()
                GoToStore()
                FilterRequest()
            }
        }

    }
}

@Preview
@Composable
fun Header() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .height(50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.check_logo_ajustado),
                contentDescription = "logo"
            )
            Box {
                Row(
                    modifier = Modifier.width(70.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "cuenta",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "notifications",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun GoToStore() {
    Prueba_tecnicaTheme(dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .height(45.dp)
                .padding(horizontal = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Storefront,
                        contentDescription = "store",
                        tint = Color.White,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 5.dp)
                    )
                    Text(
                        text = "Todas las tiendas",
                        color = Color.White,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = "goToStores",
                    tint = Color.White,
                    modifier = Modifier.rotate(180f)
                )
            }
        }
    }
}