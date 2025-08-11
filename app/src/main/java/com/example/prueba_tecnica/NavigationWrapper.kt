package com.example.prueba_tecnica

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.prueba_tecnica.ui.activities.RequestList
import com.example.prueba_tecnica.ui.activities.SigninScreen
import com.example.prueba_tecnica.ui.viewmodel.SigninViewModel

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun NavigationWrapper(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "initial") {

        composable("initial") {
            val context = LocalContext.current
            SigninScreen(
                SigninViewModel(context),
                navigateToHome = {
                    navController.navigate("home") {
                        popUpTo("initial") { inclusive = true }
                    }
                })
        }

        composable("home") {
            RequestList()
        }
    }
}