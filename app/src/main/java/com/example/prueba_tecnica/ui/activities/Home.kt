package com.example.prueba_tecnica.ui.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.prueba_tecnica.ui.components.NavBar
import com.example.prueba_tecnica.ui.components.TopBar
import com.example.prueba_tecnica.ui.theme.Prueba_tecnicaTheme

@Composable
fun Home(content: @Composable () -> Unit) {
    Prueba_tecnicaTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .navigationBarsPadding(),
        ) {
            Scaffold(
                topBar = { TopBar() },
                bottomBar = { NavBar() }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(innerPadding)
                        .padding(horizontal = 18.dp, vertical = 5.dp),
                ) {
                    content()
                }
            }
        }
    }
}