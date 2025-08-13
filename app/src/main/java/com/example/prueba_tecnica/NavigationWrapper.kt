package com.example.prueba_tecnica

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.prueba_tecnica.ui.activities.ReportDetail
import com.example.prueba_tecnica.ui.activities.ReportDetailTwo
import com.example.prueba_tecnica.ui.activities.ReportList
import com.example.prueba_tecnica.ui.activities.SigninScreen
import com.example.prueba_tecnica.ui.viewmodel.ResponseViewModel
import com.example.prueba_tecnica.ui.viewmodel.SigninViewModel
import com.example.prueba_tecnica.data.network.RetrofitClient
import com.example.prueba_tecnica.ui.viewmodel.ResponseViewModelFactory


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
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0

            val apiService = RetrofitClient.webService
            val factory = ResponseViewModelFactory(apiService)
            val responseViewModel: ResponseViewModel = viewModel(factory = factory)

            ReportDetail(id, navController, responseViewModel)
        }

        composable("reportDetailTwo") { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry("reportDetail/{id}")
            }
            val apiService = RetrofitClient.webService
            val factory = ResponseViewModelFactory(apiService)
            val responseViewModel: ResponseViewModel = viewModel(parentEntry, factory = factory)

            ReportDetailTwo(navController, responseViewModel)
        }


    }
}