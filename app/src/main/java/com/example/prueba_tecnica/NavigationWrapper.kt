package com.example.prueba_tecnica

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.prueba_tecnica.ui.activities.SigninScreen

@Composable
fun NavigationWrapper(navController: NavHostController){
    NavHost(navController = navController, startDestination = "initial"){
        composable("initial"){
            SigninScreen()
        }
    }
}