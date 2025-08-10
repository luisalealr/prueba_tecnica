package com.example.prueba_tecnica.ui.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.prueba_tecnica.ui.theme.Prueba_tecnicaTheme
import com.example.prueba_tecnica.ui.viewmodel.SigninViewModel

//viewModel: SigninViewModel, navigateToHome: () -> Unit
@Composable
fun SigninScreen() {
    Prueba_tecnicaTheme {
        Signin()
    }
}

@Preview
@Composable
fun Signin() {
    Box() {
        Column(modifier = Modifier.fillMaxWidth()){
            Text(
                text = "¡Hola!",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.displayLarge,
            )
        }
    }
}