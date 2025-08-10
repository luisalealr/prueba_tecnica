package com.example.prueba_tecnica

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen();

        super.onCreate(savedInstanceState)

        screenSplash.setKeepOnScreenCondition {false}
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
        finish()

    }
}
