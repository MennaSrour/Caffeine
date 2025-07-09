package com.menna.caffeine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.menna.caffeine.ui.navigation.CaffieneNavGraph
import com.menna.caffeine.ui.theme.CaffeineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CaffeineTheme {
                val navController = rememberNavController()
                CaffieneNavGraph(navController)
            }
        }
    }
}
