package com.example.prueba_tecnica

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.prueba_tecnica.ui.activities.ReportDetail
import com.example.prueba_tecnica.ui.activities.ReportList
import com.example.prueba_tecnica.ui.activities.SigninScreen
import com.example.prueba_tecnica.ui.viewmodel.SigninViewModel

@RequiresApi(Build.VERSION_CODES.O)
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
            ReportList(navController)
        }

        composable(
            "reportDetail/{id}",
            arguments = listOf(navArgument ("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            ReportDetail(id, navController)
        }
    }
}