package com.example.prueba_tecnica.ui.activities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.prueba_tecnica.data.helper.TokenManager
import com.example.prueba_tecnica.data.network.RetrofitClient
import com.example.prueba_tecnica.ui.components.ReportCard
import com.example.prueba_tecnica.ui.theme.Prueba_tecnicaTheme
import com.example.prueba_tecnica.ui.viewmodel.ResponseViewModel
import com.example.prueba_tecnica.ui.viewmodel.ResponseViewModelFactory

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReportList(navController: NavController) {
    val apiService = RetrofitClient.webService
    val factory = ResponseViewModelFactory(apiService)
    val responseViewModel: ResponseViewModel = viewModel(factory = factory)

    Prueba_tecnicaTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Home {
                List(responseViewModel, navController)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun List(responseViewModel: ResponseViewModel, navController: NavController) {
    val context = LocalContext.current
    val reportList by responseViewModel.requestList
    var token by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(true) {
        token = TokenManager.getToken(context)
        token?.let {
            responseViewModel.loadReports(it)
        } ?: println("No se obtuvo token")
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(0.95f)
        ) {
            items(reportList, key = { it.id }) { report ->
                ReportCard(report,
                    onClick = {
                        navController.navigate("reportDetail/${report.id}")
                    }
                )
            }
        }
    }
}
