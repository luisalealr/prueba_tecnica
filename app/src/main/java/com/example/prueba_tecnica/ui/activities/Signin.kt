package com.example.prueba_tecnica.ui.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.prueba_tecnica.ui.theme.Prueba_tecnicaTheme
import com.example.prueba_tecnica.ui.viewmodel.SigninViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun SigninScreen(viewModel: SigninViewModel, navigateToHome: () -> Unit) {
    Prueba_tecnicaTheme(dynamicColor = false) {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Signin(viewModel, navigateToHome)
        }
    }
}


@Composable
fun Signin(viewModel: SigninViewModel, navigateToHome: () -> Unit) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Text(
                text = "¡Hola!",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = "Inicia sesión",
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.padding(16.dp))
            CustomTextField(
                value = email,
                onValueChange = { viewModel.onLoginChange(it, password) },
                placeholderText = "Ingresa tu correo electrónico",
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.padding(10.dp))
            // Campo de Password
            CustomTextField(
                value = password,
                onValueChange = { viewModel.onLoginChange(email, it) },
                placeholderText = "Ingresa tu contraseña",
                keyboardType = KeyboardType.Password,
                isPassword = true
            )
            Spacer(modifier = Modifier.padding(16.dp))
            BotonIniciarSesion(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                loginEnable,
                email,
                password,
                viewModel,
                navigateToHome
            )
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false
) {
    val visualTransformation =
        if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(placeholderText) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        maxLines = 1,
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = Color.LightGray,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun BotonIniciarSesion(
    modifier: Modifier,
    loginEnable: Boolean,
    email: String,
    password: String,
    viewModel: SigninViewModel,
    navigateToHome: () -> Unit,
) {

    var showErrorDialog by remember { mutableStateOf(false) }

    Box(modifier) {
        Button(
            onClick = {
                viewModel.signInUser(email, password) { success ->
                    if (success) {
                        navigateToHome()
                    } else {
                        showErrorDialog = true
                    }
                }
            },
            shape = RoundedCornerShape(5.dp),
            enabled = loginEnable,
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                disabledContentColor = Color.White
            )
        ) {
            Text("Iniciar Sesión")
        }

        if (showErrorDialog) {
            AlertDialog(
                onDismissRequest = { showErrorDialog = false },
                title = { Text("Error") },
                text = { Text("Usuario o contraseña incorrectos") },
                confirmButton = {
                    Button(
                        onClick = { showErrorDialog = false },
                        colors = ButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White,
                            disabledContainerColor = MaterialTheme.colorScheme.surface,
                            disabledContentColor = Color.White
                        )
                    ) {
                        Text("Aceptar")
                    }
                }
            )
        }
    }
}